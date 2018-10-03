package com.panther.study.dubbo.lesson01.chapter06.test;

import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.io.IOException;

/**
 * @author: Kevin
 * @date: created in 下午9:46 2018-10-03
 * @version: V1.0
 */
public class Provider {

	public static void main(String[] args) throws IOException {
		ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext(new String[]{"provider.xml"});
		context.start();
		System.in.read();
	}
}
