package com.panther.study.threadandrunnable03.chapter23.example02;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author: Kevin
 * @date: created in 下午5:48 2018-11-10
 * @version: V1.0
 */
public abstract class Latch {

	// 用于控制多少个线程完成任务时才能打开阀门
	protected AtomicInteger limit;

	public Latch(AtomicInteger limit){
		this.limit = limit;
	}

	// 该方法会使得当前线程一直等待， 直到所有线程的工作完成， 被阻塞的线程是允许被中断的
	public abstract void await();

	/**
	 *
	 * @param unit 等待时间单位
	 * @param time 指定的数量
	 */
	public abstract void await(TimeUnit unit, long time);
	// 当任务线程完成工作后调用的该方法使得计数器减一
	public abstract void countDown();

	// 获取当前还有多少个线程没有完成任务
	public abstract int getUnarrived();

}
