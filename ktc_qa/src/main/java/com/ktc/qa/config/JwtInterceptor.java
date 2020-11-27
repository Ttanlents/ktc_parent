package com.ktc.qa.config;

import io.jsonwebtoken.Claims;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;
import util.JwtUtil;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author 余俊锋
 * @date 2020/11/26 10:09
 * @Description
 */
@Component
public class JwtInterceptor extends HandlerInterceptorAdapter {
    @Autowired
    JwtUtil jwtUtil;

    @Autowired
    Environment environment ;
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String auth = request.getHeader("auth");
        String prefix=environment.getProperty("jwt.config.prefix");
        if (auth!=null&&!"".equals(auth)&&auth.startsWith(prefix)){
            String token=auth.substring(prefix.length());
            Claims claims = jwtUtil.parseJWT(token);
            if (claims.get("role").equals("admin")){
                request.setAttribute("claims_admin",claims);
            }else if(claims.get("role").equals("user")){
                request.setAttribute("claims_user",claims);
            }
        }
        return true;
    }
}
