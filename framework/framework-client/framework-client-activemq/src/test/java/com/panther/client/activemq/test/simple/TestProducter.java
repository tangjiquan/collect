package com.panther.client.activemq.test.simple;

import com.panther.client.activemq.simple.Producter;

/**
 * @author: Kevin
 * @date: created in 上午12:03 2018-09-25
 * @version: V1.0
 */
public class TestProducter {

	public static void main(String[] args){
		Producter producter = new Producter();
		producter.init();
		TestProducter testProducter = new TestProducter();
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		//Thread 1
		new Thread(testProducter.new ProductorMq(producter)).start();
		//Thread 2
		new Thread(testProducter.new ProductorMq(producter)).start();
		//Thread 3
		new Thread(testProducter.new ProductorMq(producter)).start();
		//Thread 4
		new Thread(testProducter.new ProductorMq(producter)).start();
		//Thread 5
		new Thread(testProducter.new ProductorMq(producter)).start();
	}

	private class ProductorMq implements Runnable{
		Producter producter;
		public ProductorMq(Producter producter){
			this.producter = producter;
		}

		@Override
		public void run() {
			while(true){
				try {
					producter.sendMessage("Jaycekon-MQ");
					Thread.sleep(10000);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		}
	}
}
