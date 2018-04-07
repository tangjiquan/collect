package io.zbus.examples.mq.consumer;

import io.zbus.mq.*;

import java.io.IOException;

public class ConsumerExample {

	@SuppressWarnings("resource")
	public static void main(String[] args) throws Exception {   
		Broker broker = new Broker("localhost:15555");   
		
		ConsumerConfig config = new ConsumerConfig(broker);
		config.setTopic("MyTopic");   
		config.setMessageHandler(new MessageHandler() { 
			@Override
			public void handle(Message msg, MqClient client) throws IOException {
				System.out.println(msg);     
			}
		});
		
		Consumer consumer = new Consumer(config);
		consumer.start(); 
	} 
}
