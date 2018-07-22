package info.chaintech.july.web.security;

import info.chaintech.july.commons.utils.ModelMapper;
import info.chaintech.july.domain.User;
import info.chaintech.july.jwt.JwtUserDto;
import lombok.extern.slf4j.Slf4j;
import net.sf.cglib.beans.BeanCopier;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;

import java.util.Optional;

/**
 * 从 Spring Security context 获取认证的用户
 */
@Slf4j
public class SecurityHelper {

    private static ModelMapper modelMapper = new ModelMapper();

    public static JwtUserDto getJwtUser() {
        SecurityContext ctx = SecurityContextHolder.getContext();

        Optional<SecurityContext> contextOptional = Optional.ofNullable(ctx);
        JwtUserDto user = (JwtUserDto) contextOptional.map(SecurityContext::getAuthentication)
                .map(Authentication::getPrincipal)
                .filter(principal -> principal instanceof JwtUserDto).orElse(null);
        log.info("从 Spring security context 中获取认证用户, user: {}", modelMapper.prettify(user));
        return user;
    }

    public static User getUser() {
        JwtUserDto userDto = getJwtUser();
        BeanCopier copier = BeanCopier.create(JwtUserDto.class, User.class, false);
        User user = new User();
        copier.copy(userDto, user, null);
        return user;
    }

}
