package com.panther.study.pool.thread.lesson01;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.atomic.AtomicLong;

/**
 * 简单版本的线程池
 * 包含工作者容器， 任务队列
 *
 * @author: Kevin
 * @date: created in 下午10:18 2019-02-19
 * @version: V1.0
 */
public class ThreadPool <Task extends Runnable> {

	/** 最大线程数 */
	private static final int MAX_WORKER_NUMBERS = 10;
	/** 默认线程数 */
	private static final int DEFAULT_WORKER_NUMBERS = 5;
	/** 最小线程数 */
	private static final int MIN_WORKER_NUMBERS = 1;
	/** 工作队列 */
	private BlockingQueue<Task> taskQueue = new LinkedBlockingQueue<>();
	/** 工作者容器 */
	private List<Worker> workerList = Collections.synchronizedList(new ArrayList());
	/** 当前线程数 */
	private int workerNum = DEFAULT_WORKER_NUMBERS;

	private AtomicLong threadNumber = new AtomicLong();

	public ThreadPool(int number){
		this.workerNum = number > MAX_WORKER_NUMBERS ? MAX_WORKER_NUMBERS : number < MIN_WORKER_NUMBERS ? MIN_WORKER_NUMBERS : number;
		init(workerNum);
	}

	/**
	 * 预先创建固定数目的线程
	 *
	 * @param workerNum
	 */
	public void init(int workerNum){
		for(int i=0; i<workerNum; i++){
			workerList.add(new Worker("worker-" + threadNumber.incrementAndGet()));
		}

		for(Worker worker : workerList){
			new Thread(worker).start();
		}
	}

	// 执行任务
	public void execute(Task task){
		if(task != null){
			synchronized (taskQueue){
				taskQueue.add(task);
				taskQueue.notify();
			}
		}
	}

	// 关闭线程池
	public void shutdown(){
		for(Worker worker : workerList){
			worker.shutdown();
		}
	}

	/**
	 * 增加一定数目的工作者
	 *
	 * @param number
	 */
	public void addWorkers(int number){
		synchronized (workerList){
			if(number + this.workerNum > MAX_WORKER_NUMBERS){
				number = MAX_WORKER_NUMBERS - this.workerNum;
			}
			init(number);
			this.workerNum += number;
		}
	}

	/**
	 * 减少一定数量的工作者
	 *
	 * @param number
	 */
	public void removeWorkers(int number){
		synchronized (workerList){
			if(number > this.workerNum){
				throw new IllegalArgumentException("the number is more then the workerNum");
			}
			int count = 0;

			while(count < number){
				Worker worker = workerList.get(count);
				if(workerList.remove(worker)){
					worker.shutdown();
					count++;
				}
			}
		}
		this.workerNum -= number;
	}


	public class Worker implements Runnable{
		private volatile boolean isStop = false;

		private String workerName = "";

		public Worker(String workerName){
			this.workerName = workerName;
		}


		public void shutdown(){
			this.isStop = true;
			System.out.println(this.workerName + "is shutdown");
		}

		@Override
		public void run() {
			while(!isStop){
				Task task = null;
				synchronized (taskQueue){
					while(taskQueue.isEmpty()){
						try {
							taskQueue.wait();
						} catch (InterruptedException e) {
							e.printStackTrace();
						}
					}
					task = taskQueue.poll();
				}
				if(task != null){
					System.out.println(this.workerName + "处理task"+task.toString());
					task.run();
				}
			}
		}
	}
}
