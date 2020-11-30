package com.ktc.config;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.config.server.EnableConfigServer;

/**
 * @author 余俊锋
 * @date 2020/11/30 10:47
 * @Description
 */
@EnableConfigServer // //开启SpringCloudConfig集中配置服务器
@SpringBootApplication
public class ConfigApplication {
    public static void main(String[] args) {
        SpringApplication.run(ConfigApplication.class);
    }
}
