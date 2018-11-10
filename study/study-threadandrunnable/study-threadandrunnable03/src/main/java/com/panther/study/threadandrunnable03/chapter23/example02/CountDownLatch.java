package com.panther.study.threadandrunnable03.chapter23.example02;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * 增加超时时间
 *
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
	public void await(TimeUnit unit, long time) {
		if(time < 0){
			throw new IllegalArgumentException("time is invalid");
		}
		long remainingNanos = unit.toNanos(time);
		final long endNanos = System.nanoTime() + remainingNanos;
		synchronized (this){
			while(limit.get() > 0){
				if(TimeUnit.NANOSECONDS.toMillis(remainingNanos)<=0){
					throw new RuntimeException("the wait time over specify");
				}
				try {
					// 等待remainingNanos的过程中可能中断， 需要重新计算remainingNanos
					this.wait(TimeUnit.NANOSECONDS.toMillis(remainingNanos));
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				remainingNanos = endNanos - System.nanoTime();
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
