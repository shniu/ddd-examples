package info.chaintech.july.jwt;

import com.google.common.base.Strings;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Optional;

/**
 * @author shniu
 * @date 2018-06-08 下午6:25
 * @email niushaohan@digcredit.com
 */

@Slf4j
public class JwtValidationFilter extends BasicAuthenticationFilter {
    private static final String HEADER_AUTHORIZATION = "Authorization";
    private static final String AUTH_BEARER = "Bearer ";

    private String jwtSecret;
    private UserDetailsService jwtUserDetailsService;

    public JwtValidationFilter(AuthenticationManager authenticationManager,
                               String jwtSecret,
                               UserDetailsService jwtUserDetailsService) {
        super(authenticationManager);
        this.jwtSecret = jwtSecret;
        this.jwtUserDetailsService = jwtUserDetailsService;
    }

    @Override
    @Transactional
    protected void doFilterInternal(HttpServletRequest req,
                                    HttpServletResponse res,
                                    FilterChain chain) throws IOException, ServletException {
        String header = req.getHeader(HEADER_AUTHORIZATION);
        log.info(">>> Authorization {} {}", req.getMethod(), req.getRequestURI());

        // 如果授权
        if (header == null || !header.startsWith(AUTH_BEARER)) {
            chain.doFilter(req, res);
            return;
        }

        UsernamePasswordAuthenticationToken authentication = getAuthentication(req);
        SecurityContextHolder.getContext().setAuthentication(authentication);
        chain.doFilter(req, res);
    }

    protected UsernamePasswordAuthenticationToken getAuthentication(HttpServletRequest req) {
        String token = req.getHeader(HEADER_AUTHORIZATION);
        log.debug("请求头里的 token: {}", token);
        return Optional.ofNullable(token)
                .filter(t -> !Strings.isNullOrEmpty(t))
                .map(t -> JwtAuthorizationHelper.parseJwt(t.replace(AUTH_BEARER, ""), jwtSecret))
                .map(claims -> (String) claims.getOrDefault(JwtAuthorizationHelper.JWT_CLAIMS_USERNAME, ""))
                .map(username -> {
                    UsernamePasswordAuthenticationToken authenticationToken = null;
                    try {

                        JwtUserDto userDetails = (JwtUserDto) jwtUserDetailsService.loadUserByUsername(username);
                        authenticationToken = new UsernamePasswordAuthenticationToken(
                                userDetails,
                                null,
                                userDetails.getAuthorities());

                        log.info(">>> 用户认证通过, 开始遨游世界 <<<");
                    } catch (UsernameNotFoundException e) {
                        log.warn("{} 用户不存在，认证失败", username);
                    }

                    return authenticationToken;
                }).orElse(null);
    }
}
