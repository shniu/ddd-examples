package info.chaintech.july.jwt;

import com.google.common.base.Strings;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import lombok.extern.slf4j.Slf4j;

import javax.crypto.spec.SecretKeySpec;
import javax.xml.bind.DatatypeConverter;
import java.security.Key;
import java.util.Date;

/**
 * @author shniu
 * @date 2018-06-07 下午7:18
 * @email niushaohan@digcredit.com
 */

@Slf4j
public class JwtAuthorizationHelper {
    public static final String JWT_CLAIMS_USERNAME = "username";
    public static final String JWT_CLAIMS_UID = "uid";
    private static final String defaultSecret = "cool.ju.miniapp";
    private static final String JWT_ISSUER_NAME = "SYPH.cool.ju";
    private static final String JWT_HEADER_ALG = "alg";
    private static final String JWT_HEADER_TYP = "typ";
    private static final String JWT = "JWT";

    /**
     * 解析 JWT
     */
    public static Claims parseJwt(String jsonWebToken, String base64Security) {
        if (Strings.isNullOrEmpty(base64Security))
            base64Security = defaultSecret;

        try {
            return Jwts.parser()
                    .setSigningKey(DatatypeConverter.parseBase64Binary(base64Security))
                    .parseClaimsJws(jsonWebToken).getBody();
        } catch (Exception e) {
            // swallow and return null
            log.warn("解析JWT出错", e);
        }
        return null;
    }

    /**
     * 生成 JWT
     */
    public static String createJwt(JwtClaims jwtClaims, String base64Security) {
        if (Strings.isNullOrEmpty(base64Security))
            base64Security = defaultSecret;

        long now = System.currentTimeMillis();
        SignatureAlgorithm signatureAlgorithm = SignatureAlgorithm.HS512;
        byte[] apiKeySecretBytes = DatatypeConverter.parseBase64Binary(base64Security);
        Key signingKey = new SecretKeySpec(apiKeySecretBytes, signatureAlgorithm.getJcaName());

        JwtBuilder builder = Jwts.builder().setHeaderParam(JWT_HEADER_TYP, JWT)
                .setHeaderParam(JWT_HEADER_ALG, signatureAlgorithm.getValue())
                .claim(JWT_CLAIMS_USERNAME, jwtClaims.getUsername())
                .claim(JWT_CLAIMS_UID, jwtClaims.getUid())
                .setSubject(jwtClaims.getUsername())
                .setIssuer(JWT_ISSUER_NAME)
                .setAudience(jwtClaims.getUsername())
                .signWith(SignatureAlgorithm.HS512, signingKey);
        if (jwtClaims.getExpiredMs() > 0) {
            builder.setExpiration(new Date(now + jwtClaims.getExpiredMs()))
                    .setNotBefore(new Date(now));
        }
        return builder.compact();
    }

}
