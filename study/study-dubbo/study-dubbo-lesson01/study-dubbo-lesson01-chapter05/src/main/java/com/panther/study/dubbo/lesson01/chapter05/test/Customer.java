package com.panther.study.dubbo.lesson01.chapter05.test;

import com.panther.study.dubbo.lesson01.chapter05.dirct.provider.DirectService;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.io.IOException;

/**
 * @author: Kevin
 * @date: created in 下午5:00 2018-10-03
 * @version: V1.0
 */
public class Customer {

	public static void main(String[] args) throws IOException {
		ClassPathXmlApplicationContext classPathXmlApplicationContext = new ClassPathXmlApplicationContext(new String[]{"direct-customer.xml"});
		classPathXmlApplicationContext.start();
		DirectService directService = (DirectService)classPathXmlApplicationContext.getBean("directService");
		String str = directService.direct();
		System.out.println(str);
		System.in.read();
	}
}
