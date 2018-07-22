package info.chaintech.july.web.controller;

import info.chaintech.july.conf.aop.CoolLogger;
import info.chaintech.july.domain.User;
import info.chaintech.july.jwt.JwtAuthorizationHelper;
import info.chaintech.july.jwt.JwtClaims;
import info.chaintech.july.service.UserService;
import info.chaintech.july.web.message.ResponseCode;
import info.chaintech.july.web.message.ResponseMessage;
import info.chaintech.july.web.vo.LoginResponse;
import info.chaintech.july.web.vo.LoginVo;
import lombok.extern.slf4j.Slf4j;
import org.apache.tools.ant.util.DateUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author shniu
 * @date 2018-06-07 下午2:49
 * @email niushaohan@digcredit.com
 */

@RestController
@RequestMapping("/api/auth")
@Validated
@Slf4j
public class AuthorizationController {
    @Value("${jwt.secret}")
    private String jwtSecret;

    @Value("${jwt.expiration}")
    private long expiredMs;

    private UserService userService;

    public AuthorizationController(UserService userService) {
        this.userService = userService;
    }

    @CoolLogger(remark = "用户登录", action = "login", targetType = "Authorization")
    @PostMapping("/login")
    public ResponseMessage<LoginResponse> login(@RequestBody @Validated LoginVo loginVo) {
        User user = userService.login(loginVo);
        log.debug("user={}", user);
        if (null == user) {
            return ResponseMessage.error(ResponseCode.Unauthorized);
        }

        JwtClaims jwtClaims = new JwtClaims();
        jwtClaims.setUid(user.getUid());
        jwtClaims.setUsername(user.getUsername());
        jwtClaims.setExpiredMs(expiredMs);
        String token = JwtAuthorizationHelper.createJwt(jwtClaims, jwtSecret);

        LoginResponse loginResponse = new LoginResponse(token, user.getRealName(),
                user.getUsername(), DateUtils.format(user.getCreatedOn(), "yyyy-MM-dd HH:mm:ss"));
        return ResponseMessage.ok(loginResponse);
    }
}
