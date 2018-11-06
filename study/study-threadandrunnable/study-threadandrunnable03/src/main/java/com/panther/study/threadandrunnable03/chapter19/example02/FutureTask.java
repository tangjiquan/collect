package com.panther.study.threadandrunnable03.chapter19.example02;

/**
 * @author: Kevin
 * @date: created in 下午9:32 2018-11-06
 * @version: V1.0
 */
public class FutureTask<T> implements Future<T> {
	private T result;

	private boolean isDone = false;

	private final Object LOCK = new Object();

	@Override
	public T get() throws InterruptedException {

		// 当任务还没完成时候， 调用get方法会被挂起而进入阻塞
		synchronized (LOCK){
			while(!isDone){
				LOCK.wait();
			}
			// 返回计算结果
			return result;
		}
	}

	/**
	 * finish方法主要用于为FutureTask设置计算结果值
	 * @param result
	 */
	protected void finish(T result){
		synchronized (LOCK){
			if(isDone){
				return;
			}
			// 计算完成， 为result指定结果， 并且isDone设置true, 同时唤醒阻塞中的线程
			this.result = result;
			this.isDone = true;
			LOCK.notifyAll();

		}
	}

	/**
	 * 返回当前任务是否已经完成
	 * @return
	 */
	@Override
	public boolean done() {
		return isDone;
	}
}
