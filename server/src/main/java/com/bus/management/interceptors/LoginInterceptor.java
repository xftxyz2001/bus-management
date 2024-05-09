package com.bus.management.interceptors;


import com.bus.management.config.Env;
import com.bus.management.result.Result;
import com.bus.management.utils.JwtUtil;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import java.io.IOException;

@Component
public class LoginInterceptor implements HandlerInterceptor {
    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    @Autowired
    private ObjectMapper objectMapper;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        if (HttpMethod.OPTIONS.matches(request.getMethod())) {
            return true;
        }
        String token = request.getHeader(HttpHeaders.AUTHORIZATION);
        if (isValidToken(token)) {
            Integer userId = JwtUtil.parseToken(token);
            request.setAttribute(Env.CURRENT_REQUEST_USER, userId);
            return true;
        } else {
            sendErrorResponse(response, "token无效");
            return false;
        }
    }

    private boolean isValidToken(String token) {
        try {
            return Boolean.TRUE.equals(stringRedisTemplate.hasKey(token));
        } catch (Exception e) {
            return false;
        }
    }

    private void sendErrorResponse(HttpServletResponse response, String message) throws IOException {
        response.setContentType("application/json;charset=utf-8");
        response.getWriter().write(objectMapper.writeValueAsString(Result.error(message)));
    }
}
