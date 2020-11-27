package com.ktc.base;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.context.annotation.Bean;
import util.IdWorker;

/**
 * @author 余俊锋
 * @date 2020/11/21 18:08
 * @Description
 */

@SpringBootApplication
@EnableEurekaClient
public class BaseApplication {
    public static void main(String[] args){
        SpringApplication.run(BaseApplication.class);
    }
    @Bean
    public IdWorker idWorker(){
        return new IdWorker();
    }

}
