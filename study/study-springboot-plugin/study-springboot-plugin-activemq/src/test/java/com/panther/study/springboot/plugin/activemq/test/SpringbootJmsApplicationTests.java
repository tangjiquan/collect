package com.panther.study.springboot.plugin.activemq.test;

import com.panther.study.springboot.plugin.activemq.Application;
import com.panther.study.springboot.plugin.activemq.Consumer;
import com.panther.study.springboot.plugin.activemq.Producer;
import org.apache.activemq.command.ActiveMQQueue;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.jms.Destination;


/**
 * @author: Kevin
 * @date: created in 上午9:34 2018-09-25
 * @version: V1.0
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = Application.class)
public class SpringbootJmsApplicationTests {

	@Autowired
	private Producer producer;

	@Autowired
	private Consumer consumer;
	/**
	 * 产生数据
	 * @throws InterruptedException
	 */
	@Test
	public void producter() throws InterruptedException {
		Destination destination = new ActiveMQQueue("mytest.queue");

		for(int i=0; i<100; i++){
			producer.sendMessage(destination, "myname is chhliu!!!"+i);
		}
	}
}
