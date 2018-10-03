package com.panther.study.dubbo.lesson01.chapter02.test;


import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.io.IOException;

/**
 * @author: Kevin
 * @date: created in 下午2:18 2018-10-02
 * @version: V1.0
 */
public class Provider {

	public static void main(String[] args) throws IOException {

		ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext(
				new String[] { "classpath:dependency-provider.xml" });
		context.start();
		System.in.read(); // 为保证服务一直开着，利用输入流的阻塞来模拟


	}
}
