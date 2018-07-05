package org.jq.controller;

import org.springframework.boot.Banner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.PropertySource;

/**
 * @Author: Kevin
 * @Date: Created in 下午4:01 18-5-1
 * @Version:
 * @Description:
 */
//会初始化那些注解：@Repository @Service @Controller @Component @Entity , 当前包或者是子包下的都会被扫描
@SpringBootApplication//告知spring是一个入口类
@PropertySource("classpath:config.properties")
public class MySpringBootApplication {

	public static void main(String[] args){
		// 第一种方式启动
		// 启动springboot, 并初始化相关属性
		//SpringApplication.run(MySpringBootApplication.class);

		// 第二种方式启动
		SpringApplication app = new SpringApplication(MySpringBootApplication.class);
		app.setBannerMode(Banner.Mode.OFF);// 关闭banner的配置
		app.run(args);

	}

}
