package com.panther.study.threadandrunnable03.chapter08.example01;

import java.util.LinkedList;

/**
 * 存放任务的队列
 *
 * @author: Kevin
 * @date: created in 下午9:11 2018-10-21
 * @version: V1.0
 */
public class LinkedRunnableQueue implements RunnableQueue {

	// 任务队列的最大容量，在构造时候传入
	private final int limit;

	// 如果队列已经满了，则需要拒绝的策略
	private final DenyPolicy denyPolicy;

	// 存放任务的队列
	private  final LinkedList<Runnable> runnableList = new LinkedList<>();

	private final ThreadPool threadPool;

	public LinkedRunnableQueue(int limit, DenyPolicy denyPolicy, ThreadPool threadPool){
		this.limit = limit;
		this.denyPolicy = denyPolicy;
		this.threadPool = threadPool;
	}
	@Override
	public void offer(Runnable runnable) {
		if(runnableList.size()>=limit){
			// 无法容纳的新任务时执行拒绝策略
			denyPolicy.reject(runnable, threadPool);
		}else{
			// 将任务加入队列尾，并唤醒阻塞的线程
			runnableList.addLast(runnable);
			runnableList.notifyAll();
		}
	}

	@Override
	public Runnable take() {
		synchronized (runnableList){
			while(runnableList.isEmpty()){
				try {
					// 如果任务队列中没有可执行的任务，则当前线程将会挂起， 进入
					// runnableList关联的monitor waitset中等待唤醒（新的任务加入）
					runnableList.wait();
				} catch (InterruptedException e) {
					// 被中断时需要将该异常抛出
					try {
						throw e;
					} catch (InterruptedException e1) {
						e1.printStackTrace();
					}
				}
			}
			// 从任务队列张移除一个任务
			return runnableList.removeFirst();
		}
	}

	@Override
	public int size() {
		return runnableList.size();
	}
}
