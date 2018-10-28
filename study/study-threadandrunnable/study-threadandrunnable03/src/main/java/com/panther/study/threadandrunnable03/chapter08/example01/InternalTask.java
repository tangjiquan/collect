package com.panther.study.threadandrunnable03.chapter08.example01;

/**
 * @author: Kevin
 * @date: created in 下午9:03 2018-10-21
 * @version: V1.0
 */
public class InternalTask implements Runnable{
	private final RunnableQueue runnableQueue;

	private volatile boolean running  = true;

	public InternalTask(RunnableQueue runnableQueue){
		this.runnableQueue = runnableQueue;
	}

	@Override
	public void run() {
		// 如果当前线程没有被中断且running状态， 则其不断的从queue中获取runnable，然后执行run方法
		while(running && !Thread.currentThread().isInterrupted()){
			try{
				Runnable task = runnableQueue.take();
				task.run();
			}catch (Exception e){
				running = false;
				break;
			}
		}
	}

	/**
	 * 停止当前的任务， 主要在线程的shutdownf方法中使用
	 */
	public void stop(){
		this.running = false;
	}
}
