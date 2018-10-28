package org.panther.study.threadandrunnable02.example17;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentLinkedQueue;

/**
 * @author: Kevin
 * @date: created in 下午8:14 2018-09-18
 * @version: V1.0
 */
public class Worker implements Runnable{

	private ConcurrentLinkedQueue<Task> workQueue;
	private ConcurrentHashMap<String, Object> resultMap;

	@Override
	public void run() {
		while(true){
			Task input = this.workQueue.poll();
			if(input == null){
				break;
			}
			Object obj = handle(input);
			this.resultMap.put(Integer.toString(input.getId()), obj);
		}


	}

	// 正常处理逻辑
	private Object handle(Task input){
		System.out.println(input.getId());
		Object object = null;
		object = input.getPrice();
		/*try {
			Thread.sleep(1000);
			object = input.getPrice();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}*/

		return object;
	}

	public void setWorkQueue(ConcurrentLinkedQueue<Task> workQueue) {
		this.workQueue = workQueue;
	}

	public void setResultMap(ConcurrentHashMap<String, Object> resultMap) {
		this.resultMap = resultMap;
	}
}
