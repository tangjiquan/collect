package com.panther.study.dubbo.lesson01.chapter02.test;


import com.panther.study.dubbo.lesson01.chapter02.dependency.provider.DependencyService;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.io.IOException;

/**
 * @author: Kevin
 * @date: created in 下午2:37 2018-10-02
 * @version: V1.0
 */
public class Customer {

	public static void main(String[] args) throws IOException {

		ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext(
				new String[] {"dependency-consumer.xml"});
		context.start();

		DependencyService service = (DependencyService) context.getBean("dependencyServiceCustomer");
		String hello = service.dependency("tom");
		System.out.println(hello);

		System.in.read(); // 为保证服务一直开着，利用输入流的阻塞来模拟
	}
}
