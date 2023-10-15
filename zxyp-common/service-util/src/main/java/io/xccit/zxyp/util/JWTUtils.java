package io.xccit.zxyp.util;

/**
 * @author CH_ywx
 * @date 2023/10/15
 * @description
 */

import io.jsonwebtoken.*;

import java.util.Date;

/**
 * @author CH_ywx
 * @date 2023/10/15
 * @description JWT工具类
 */
public class JWTUtils {
    private static final long tokenExpiration = 7*24*60*60*1000; //过期时间
    private static final String tokenSignKey = "xccit-zxyp"; //密钥
    private static final String tokenSubject = "ZXYP-USER"; //

    /**
     * 根据用户名和用户ID创建token
     * @param userID
     * @param userName
     * @return
     */
    public static String createToken(Long userID,String userName){
        String token = Jwts.builder().setExpiration(new Date(System.currentTimeMillis() + tokenExpiration))
                .setSubject(tokenSubject)
                .claim("userID", userID)
                .claim("userName", userName)
                .signWith(SignatureAlgorithm.HS512, tokenSignKey)
                .compressWith(CompressionCodecs.GZIP)
                .compact();
        return token;
    }

    /**
     * 根据token获取用户ID
     * @param token
     * @return
     */
    public static Long getUserID(String token){
        Jws<Claims> claimsJws = Jwts.parser().setSigningKey(tokenSignKey).parseClaimsJws(token);
        Claims claims = claimsJws.getBody();
        Integer userID = (Integer) claims.get("userID");
        return userID.longValue();
    }

    /**
     * 根据token获取userName
     * @param token
     * @return
     */
    public static String getUserName(String token){
        Jws<Claims> claimsJws = Jwts.parser().setSigningKey(tokenSignKey).parseClaimsJws(token);
        Claims claims = claimsJws.getBody();
        String userName = (String) claims.get("userName");
        return userName;
    }
}

