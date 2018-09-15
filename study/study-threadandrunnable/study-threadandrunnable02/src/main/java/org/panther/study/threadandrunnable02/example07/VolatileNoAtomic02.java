package org.panther.study.threadandrunnable02.example07;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * volatile关键字不具备synchronized关键字的原子性（同步）
 *
 * @author: Kevin
 * @date: created in 上午12:03 2018-09-06
 * @version: V1.0
 */
public class VolatileNoAtomic02 extends  Thread{

	//private static volatile int count;
	private static AtomicInteger count = new AtomicInteger(0);
	private static void addCount(){
		for(int i=0; i<1000; i++){
			count.incrementAndGet();
		}
		System.out.println(count);
	}

	@Override
	public void run() {
		addCount();
	}

	public static void main(String[] args){
		VolatileNoAtomic02[] arr = new VolatileNoAtomic02[10];
		for(int i=0;i<10;i++){
			arr[i] = new VolatileNoAtomic02();
		}

		for(int i=0; i<10; i++){
			arr[i].start();
		}
	}

}
