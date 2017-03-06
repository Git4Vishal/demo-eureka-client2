package com.example;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.netflix.feign.EnableFeignClients;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication
//@ComponentScan(value = { "com.example" })
@EnableEurekaClient
@EnableFeignClients(/*basePackages = { "com.example" }*/)
public class DemoEurekaClient2Application {
	
	@Bean 
	@LoadBalanced 
	RestTemplate restTemplate() { 
		return new RestTemplate(); 
	} 

	
	public static void main(String[] args) {
		SpringApplication.run(DemoEurekaClient2Application.class, args);
	}
}