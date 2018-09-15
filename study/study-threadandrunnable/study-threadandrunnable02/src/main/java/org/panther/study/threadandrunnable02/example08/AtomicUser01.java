package org.panther.study.threadandrunnable02.example08;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author: Kevin
 * @date: created in 下午8:36 2018-09-08
 * @version: V1.0
 */
public class AtomicUser01 {

	private static AtomicInteger count = new AtomicInteger(0);

	public int multAdd() {
		try {
			Thread.sleep(100);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

		count.addAndGet(1);
		count.addAndGet(2);
		count.addAndGet(3);
		count.addAndGet(4);
		return count.get();
	}

	public static void main(String[] args){
		final AtomicUser01 au = new AtomicUser01();
		List<Thread> ts = new ArrayList<Thread>();
		for(int i=0; i<100; i++){
			ts.add(new Thread(new Runnable() {
				@Override
				public void run() {
					System.out.println(au.multAdd());
				}
			}));
		}

		for(Thread t : ts){
			t.start();
		}
	}
}
