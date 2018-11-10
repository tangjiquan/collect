package com.panther.study.threadandrunnable03.chapter23.example01;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author: Kevin
 * @date: created in 下午6:04 2018-11-10
 * @version: V1.0
 */
public class CountDownLatch extends Latch {


	public CountDownLatch(AtomicInteger limit) {
		super(limit);
	}

	@Override
	public void await() {
		synchronized (this){
			while (limit.get()>0){
				// 当limit>0时，当前线程进入阻塞状态
				try {
					this.wait();
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		}
	}


	@Override
	public void countDown() {
		synchronized (this){
			if(limit.get()<=0){
				throw new IllegalStateException("all of task already arrived");
			}
			limit.decrementAndGet();
			this.notifyAll();
		}
	}

	@Override
	public int getUnarrived() {
		return limit.get();
	}
}
