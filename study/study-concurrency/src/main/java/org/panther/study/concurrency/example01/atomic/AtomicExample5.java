package org.panther.study.concurrency.example01.atomic;

import java.util.concurrent.atomic.AtomicIntegerFieldUpdater;

/**
 * @author: Kevin
 * @date: created in 下午11:14 2018-07-07
 * @version: V1.0
 */
public class AtomicExample5 {

	private static AtomicIntegerFieldUpdater<AtomicExample5> updater = AtomicIntegerFieldUpdater.newUpdater(AtomicExample5.class, "count");

	private volatile int count = 100;

	public int getCount() {
		return count;
	}

	public void setCount(int count) {
		this.count = count;
	}

	public static void main(String[] args){
		AtomicExample5 atomicExample5 = new AtomicExample5();
		if(updater.compareAndSet(atomicExample5, 100, 200)){
			System.out.println("success1！！！" + atomicExample5.getCount());
		}
		if(updater.compareAndSet(atomicExample5, 100, 200)){
			System.out.println("success12222" + atomicExample5.getCount());
		}else{
			System.out.println("error！！！" + atomicExample5.getCount());
		}
	}




}
