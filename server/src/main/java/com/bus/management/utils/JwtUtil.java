package com.bus.management.utils;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;

public class JwtUtil {

    // 用户id
    public static final String X_USER_ID = "x-user-id";
    // 过期时间
    public static final long EXPIRE_TIME = 60 * 60 * 1000;
    // 密钥
    private static final String SECRET_KEY = "bus-secret-key";

    // 生成token
    public static String generateToken(Integer userId) {
        return JWT.create().withClaim(X_USER_ID, userId)
                .withExpiresAt(new java.util.Date(System.currentTimeMillis() + EXPIRE_TIME))
                .sign(Algorithm.HMAC256(SECRET_KEY));
    }

    // 解析token
    public static Integer parseToken(String token) {
        return JWT.require(Algorithm.HMAC256(SECRET_KEY))
                .build()
                .verify(token)
                .getClaim(X_USER_ID)
                .asInt();
    }

}
