package com.panther.study.dubbo.lesson01.chapter06.test;

import com.panther.study.dubbo.lesson01.chapter06.entity.Simple;
import com.panther.study.dubbo.lesson01.chapter06.provider.SimpleService;
import com.panther.study.dubbo.lesson01.chapter06.provider.UserService;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.io.IOException;

/**
 * @author: Kevin
 * @date: created in 下午9:48 2018-10-03
 * @version: V1.0
 */
public class Consumer {

	public static void main(String[] args) throws IOException {
		ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext(new String[]{"customer.xml"});
		context.start();

		SimpleService ss = (SimpleService) context.getBean("simpleService");
		String hello = ss.sayHello("tom");
		System.out.println(hello);
		Simple simple = ss.getSimple();
		System.out.println(simple.getName());
		System.out.println(simple.getAge());
		System.out.println(simple.getMap().get("zhang1"));


		UserService us = (UserService) context.getBean("userService");

		System.out.println(us.getUser().getName());
		System.out.println(us.getUser().getId());
	}
}
