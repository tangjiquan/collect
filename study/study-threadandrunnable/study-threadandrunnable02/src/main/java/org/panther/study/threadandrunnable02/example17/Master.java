package org.panther.study.threadandrunnable02.example17;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentLinkedQueue;

/**
 * @author: Kevin
 * @date: created in 下午8:20 2018-09-18
 * @version: V1.0
 */
public class Master {

	// 盛放任务的容器
	private ConcurrentLinkedQueue<Task> workQueue = new ConcurrentLinkedQueue<Task>();

	// 盛放work的集合
	private HashMap<String, Thread> workers = new HashMap<String, Thread>();

	// 盛放每个任务worker执行的结果集
	private ConcurrentHashMap<String, Object> resultMap = new ConcurrentHashMap<String, Object>();

	public Master(Worker worker, int workerCount){
		worker.setWorkQueue(workQueue);
		worker.setResultMap(resultMap);

		for(int i=0; i<workerCount;i++){
			this.workers.put(Integer.toString(i), new Thread(worker));
		}
	}

	/**
	 * 添加任务
	 * @param task
	 */
	public void submit(Task task){
		this.workQueue.add(task);
	}

	public void exec(){
		for(Map.Entry<String, Thread> entry : workers.entrySet()){
			entry.getValue().start();
		}
	}

	public boolean isComplate(){
		for(Map.Entry<String, Thread> entry : workers.entrySet()){
			if(entry.getValue().getState() != Thread.State.TERMINATED){
				return false;
			}
		}
		return true;
	}

	public int getResult(){
		int priceTotal = 0;
		for(Map.Entry<String, Object> entry : resultMap.entrySet()){
			priceTotal += (int)entry.getValue();
		}
		return priceTotal;
	}

}
