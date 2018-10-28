package com.panther.study.springboot.plugin.activemq.test;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author: Kevin
 * @date: created in 下午11:29 2018-09-29
 * @version: V1.0
 */
public class Test01 {

	static class Counter {
		private static AtomicInteger count = new AtomicInteger(0);
		public static void increment() {
			count.incrementAndGet();
		}
		public static int value() {
			return count.get();
		}
	}

	public static void main(String[] args) throws InterruptedException {
		while (true) {
			Counter.increment();
			System.out.println("counter: " + Counter.value());
			TimeUnit.SECONDS.sleep(1);
		}
	}
}
