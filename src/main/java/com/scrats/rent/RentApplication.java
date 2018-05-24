package com.scrats.rent;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.session.data.redis.config.annotation.web.http.EnableRedisHttpSession;

@SpringBootApplication
@MapperScan("com.scrats.rent.dao")
@EnableCaching//启用缓存
@EnableRedisHttpSession//redis session共享
public class RentApplication {

	public static void main(String[] args) {
		SpringApplication.run(RentApplication.class, args);
	}
}
