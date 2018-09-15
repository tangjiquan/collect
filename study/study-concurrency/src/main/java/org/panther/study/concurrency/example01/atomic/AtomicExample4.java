package org.panther.study.concurrency.example01.atomic;

import java.util.concurrent.atomic.AtomicReference;

/**
 * @author: Kevin
 * @date: created in 下午11:14 2018-07-07
 * @version: V1.0
 */
public class AtomicExample4 {


	private static AtomicReference<Integer> count = new AtomicReference<Integer>(0);

	public static void main(String[] args){
		count.compareAndSet(0, 1);
		count.compareAndSet(1, 2);
		count.compareAndSet(2, 5);
		count.compareAndSet(5, 1);
		System.out.println(count.get());
	}




}
