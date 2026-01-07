package com.caching_proxy;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@EnableCaching
@SpringBootApplication
public class CachingProxyApplication {

	public static void main(String[] args) {
		SpringApplication.run(CachingProxyApplication.class, args);
	}

}
