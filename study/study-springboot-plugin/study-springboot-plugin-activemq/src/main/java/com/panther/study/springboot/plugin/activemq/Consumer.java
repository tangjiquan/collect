package com.panther.study.springboot.plugin.activemq;

import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;

/**
 * @author: Kevin
 * @date: created in 上午9:33 2018-09-25
 * @version: V1.0
 */
@Component
public class Consumer {
	@JmsListener(destination = "mytest.queue")
	public void receiveQueue(String text) {
		System.out.println("Consumer收到的报文为:"+text);
	}

}
