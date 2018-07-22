package info.chaintech.july.jwt;

import info.chaintech.july.commons.utils.ResponseUtil;
import info.chaintech.july.web.message.ResponseCode;
import info.chaintech.july.web.message.ResponseMessage;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.Serializable;

/**
 * @author shniu
 * @date 2018-06-08 下午6:36
 * @email niushaohan@digcredit.com
 */

@Slf4j
public class JwtAuthenticationEntryPoint implements AuthenticationEntryPoint, Serializable {

    private static final long serialVersionUID = -8970718410437077606L;

    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response,
                         AuthenticationException authException) throws IOException, ServletException {
        // This is invoked when user tries to access a secured REST resource without supplying any credentials
        // We should just send a 401 Unauthorized response because there is no 'login page' to redirect to
        // log.info("before response status: {}", response.getStatus());
        // response.sendError(HttpServletResponse.SC_UNAUTHORIZED, "Unauthorized");
        response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
        ResponseUtil.writeResponse(response, ResponseMessage.error(ResponseCode.Unauthorized));
        log.info("认证失败, 响应状态: {}", response.getStatus());
    }
}
