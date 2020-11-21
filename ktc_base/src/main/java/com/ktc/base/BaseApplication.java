package com.ktc.base;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import utils.IdWorker;

/**
 * @author 余俊锋
 * @date 2020/11/21 18:08
 * @Description
 */

@SpringBootApplication
public class BaseApplication {
    public static void main(String[] args){
        SpringApplication.run(BaseApplication.class);
    }
    @Bean
    public IdWorker idWorker(){
        return new IdWorker();
    }

}
