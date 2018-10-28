package com.panther.study.threadandrunnable03.chapter08.example01;

/**
 * @author: Kevin
 * @date: created in 下午8:54 2018-10-21
 * @version: V1.0
 */
public interface RunnableQueue {

	/**
	 * 当有新的任务进来时首先会offer到队列中
	 * @param runnable
	 */
	void offer(Runnable runnable);

	/**
	 * 工作线程通过take方法获取Runnable
	 * @return
	 */
	Runnable take();

	/**
	 * 获取队列中的任务数量
	 * @return
	 */
	int size();

}
