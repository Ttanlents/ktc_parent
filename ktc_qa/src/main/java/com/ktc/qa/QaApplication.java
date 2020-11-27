package com.ktc.qa;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;
import util.IdWorker;
import util.JwtUtil;

/**
 * @Description
 * @author admin
 * @date 2020-11-23 18:14:28
*/
@SpringBootApplication
@EnableEurekaClient
@EnableDiscoveryClient  //开启发现服务
@EnableFeignClients   //开启作为客户端     调用发现服务
public class QaApplication {

	public static void main(String[] args) {
		SpringApplication.run(QaApplication.class, args);
	}

	@Bean
	public IdWorker idWorker(){
		return new IdWorker();
	}

	@Bean
	public JwtUtil getjwt(){
		return    new JwtUtil();
	}


}
