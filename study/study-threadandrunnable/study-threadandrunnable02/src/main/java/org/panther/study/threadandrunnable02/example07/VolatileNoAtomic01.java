package org.panther.study.threadandrunnable02.example07;

/**
 * volatile关键字不具备synchronized关键字的原子性（同步）
 * 多个线程操作一个属性的时候出现不一样的
 *
 * @author: Kevin
 * @date: created in 上午12:03 2018-09-06
 * @version: V1.0
 */
public class VolatileNoAtomic01 extends  Thread{

	private static volatile int count;

	private static void addCount(){
		for(int i=0; i<1000; i++){
			count++;
		}
		System.out.println(count);
	}

	@Override
	public void run() {
		addCount();
	}

	public static void main(String[] args){
		VolatileNoAtomic01[] arr = new VolatileNoAtomic01[10];
		for(int i=0;i<10;i++){
			arr[i] = new VolatileNoAtomic01();
		}

		for(int i=0; i<10; i++){
			arr[i].start();
		}
	}

}
