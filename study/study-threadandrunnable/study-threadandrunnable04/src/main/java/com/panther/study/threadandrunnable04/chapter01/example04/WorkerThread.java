package com.panther.study.threadandrunnable04.chapter01.example04;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

/**
 * 工作者线程
 *
 * @author: Kevin
 * @date: created in 下午10:32 2018-11-22
 * @version: V1.0
 */
public class WorkerThread {

	public static void main(String[] args){
		Helper helper = new Helper();
		helper.init();

		helper.submit("something ...");
	}


	static class Helper{
		private final BlockingQueue<String> workQueue = new ArrayBlockingQueue<String>(100);

		// 用于处理队列workQueue中的任务的工作者线程
		private final Thread workerThread = new Thread(){
			@Override
			public void run() {
				String task = null;
				while(true){
					try {
						task = workQueue.take();
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
					System.out.println(doProcess(task));
				}

			}
		};

		public void init(){
			workerThread.start();
		}

		protected String doProcess(String task){
			return task + "->process";
		}

		public void submit(String task){
			try {
				workQueue.put(task);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}

	}

}
