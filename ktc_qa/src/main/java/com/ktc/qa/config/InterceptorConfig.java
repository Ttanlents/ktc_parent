package com.ktc.qa.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;

/**
 * @author 余俊锋
 * @date 2020/11/26 10:18
 * @Description
 */
@Configuration
public class InterceptorConfig  extends WebMvcConfigurationSupport {

    @Autowired
    private JwtInterceptor jwtInterceptor;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(jwtInterceptor).
                addPathPatterns("/**").             //拦截所有请求
                excludePathPatterns("/**/login");   //放行任意请求下的/login请求

    }

}
