package com.panther.study.dubbo.lesson01.chapter01.sample.test;

import com.panther.study.dubbo.lesson01.chapter01.sample.cumtomer.SampleService;
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
				new String[] { "samplecustomer.xml" });
		context.start();

		SampleService sampleService = (SampleService) context.getBean("sampleServiceCustomer");
		String hello = sampleService.sayHello("tom");
		System.out.println(hello);
	}
}
