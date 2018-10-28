package com.panther.client.activemq.test.simple;

import com.panther.client.activemq.simple.Comsumer;

/**
 * @author: Kevin
 * @date: created in 上午12:03 2018-09-25
 * @version: V1.0
 */
public class TestConsumer {
	public static void main(String[] args){
		Comsumer comsumer = new Comsumer();
		comsumer.init();
		TestConsumer testConsumer = new TestConsumer();
		new Thread(testConsumer.new ConsumerMq(comsumer)).start();
		new Thread(testConsumer.new ConsumerMq(comsumer)).start();
		new Thread(testConsumer.new ConsumerMq(comsumer)).start();
		new Thread(testConsumer.new ConsumerMq(comsumer)).start();
	}

	private class ConsumerMq implements Runnable{
		Comsumer comsumer;
		public ConsumerMq(Comsumer comsumer){
			this.comsumer = comsumer;
		}

		@Override
		public void run() {
			while(true){
				try {
					comsumer.getMessage("Jaycekon-MQ");
					Thread.sleep(10000);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		}
	}

}
