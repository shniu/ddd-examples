package info.chaintech.july.jwt;

import lombok.Data;

/**
 * @author shniu
 * @date 2018-06-07 下午7:34
 * @email niushaohan@digcredit.com
 */

@Data
public class JwtClaims {
    private long uid;
    private String username;
    private long expiredMs;
}
