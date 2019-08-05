package org.panther.study.motan.example01;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * @author: Kevin
 * @date: created in 下午11:15 2019-03-09
 * @version: V1.0
 */
public class Client {
	public static void main(String[] args) throws InterruptedException {
		ApplicationContext ctx = new ClassPathXmlApplicationContext("classpath:motan_client.xml");
		FooService service = (FooService) ctx.getBean("remoteService");
		System.out.println(service.hello("motan"));
	}
}
