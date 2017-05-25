package com.enn.greatframework.gateway.server;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.netflix.feign.EnableFeignClients;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;
import org.springframework.context.annotation.ComponentScan;

@EnableEurekaClient
@EnableZuulProxy
@EnableFeignClients
@SpringBootApplication
@ComponentScan(basePackages = { "com.enn.howmuch.greatframework.authorize.api",
        "com.enn.greatframework.gateway.server" })
public class Application {

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}
}