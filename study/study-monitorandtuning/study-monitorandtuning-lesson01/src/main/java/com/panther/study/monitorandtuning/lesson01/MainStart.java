package com.panther.study.monitorandtuning.lesson01;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;

/**
 * @author: Kevin
 * @date: created in 上午12:10 2018-10-01
 * @version: V1.0
 */

@SpringBootApplication
public class MainStart extends SpringBootServletInitializer{

	public static void main(String[] args){
		SpringApplication.run(MainStart.class, args);
	}

	@Override
	protected SpringApplicationBuilder configure(SpringApplicationBuilder builder) {
		return builder.sources(MainStart.class);
	}
}
