package io.zbus.unittests.mq.server;

import io.zbus.mq.*;
import io.zbus.mq.server.MqServer;

import java.io.IOException;

public class MqServerTest {

	@SuppressWarnings("resource")
	public static void main(String[] args) throws Exception {  
		MqServer server = new MqServer(); //MqServer is also a Tracker by default
		server.start(); 
		
		Broker broker = new Broker(server); 
		//broker.addTracker(server);
		
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
