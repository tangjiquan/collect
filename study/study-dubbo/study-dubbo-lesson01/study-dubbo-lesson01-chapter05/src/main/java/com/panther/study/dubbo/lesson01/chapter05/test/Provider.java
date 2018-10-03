package com.panther.study.dubbo.lesson01.chapter05.test;

import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.io.IOException;

/**
 * @author: Kevin
 * @date: created in 下午4:44 2018-10-03
 * @version: V1.0
 */
public class Provider {

	public static void main(String[] args) throws IOException {
		ClassPathXmlApplicationContext classPathXmlApplicationContext = new ClassPathXmlApplicationContext(new String[]{"direct-provider.xml"});
		classPathXmlApplicationContext.start();
		System.in.read();// 为保证服务一直开着， 利用输入流的阻塞来模拟
	}
}
