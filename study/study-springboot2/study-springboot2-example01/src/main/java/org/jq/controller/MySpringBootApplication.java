package org.jq.controller;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @Author: Kevin
 * @Date: Created in 下午4:01 18-5-1
 * @Version:
 * @Description:
 */
//会初始化那些注解：@Repository @Service @Controller @Component @Entity , 当前包或者是子包下的都会被扫描
@SpringBootApplication//告知spring是一个入口类
public class MySpringBootApplication {

	public static void main(String[] args){

		// 启动springboot, 并初始化相关属性
		SpringApplication.run(MySpringBootApplication.class);
	}

}
