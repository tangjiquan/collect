package org.panther.study.motan.example01;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * @author: Kevin
 * @date: created in 下午11:14 2019-03-09
 * @version: V1.0
 */
public class Server {

	public static void main(String[] args) throws InterruptedException {
		ApplicationContext applicationContext = new ClassPathXmlApplicationContext("classpath:motan_server.xml");
		System.out.println("server start...");
	}
}
