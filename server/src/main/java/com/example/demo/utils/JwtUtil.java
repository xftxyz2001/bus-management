package com.example.demo.utils;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;

import java.util.Date;
import java.util.Map;
import java.util.UUID;

public class JwtUtil {

    private static final String KEY = "software";

    //接收业务数据,生成token并返回
    public static String genToken(Map<String, Object> claims) {
//        return JWT.create()
//                .withClaim("claims", claims)
//                .withExpiresAt(new Date(System.currentTimeMillis() + 1000 * 60 * 60 ))
//                .sign(Algorithm.HMAC256(KEY));
        String salt = UUID.randomUUID().toString().replaceAll("-", "").substring(0, 8); // 生成随机盐值
        String token = JWT.create()
                .withClaim("claims", claims)
                .withExpiresAt(new Date(System.currentTimeMillis() + 1000 * 60 * 60))
                .sign(Algorithm.HMAC256(KEY + salt)); // 使用密钥和盐值进行签名
        return token;
    }

    //接收token,验证token,并返回业务数据
    public static Map<String, Object> parseToken(String token) {
//        return JWT.require(Algorithm.HMAC256(KEY))
//                .build()
//                .verify(token)
//                .getClaim("claims")
//                .asMap();
        // 解析 token 时需要提供相同的密钥和相同的盐值
        // 在生成 token 时，我们将密钥和盐值连接起来作为签名的密钥
        String salt = token.substring(token.length() - 8); // 提取盐值
        return JWT.require(Algorithm.HMAC256(KEY + salt))
                .build()
                .verify(token)
                .getClaim("claims")
                .asMap();
    }

}
