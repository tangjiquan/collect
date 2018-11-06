package com.panther.study.threadandrunnable03.chapter20.example01;

import java.util.LinkedList;

/**
 * Suspension是挂起， 暂停的意思， Guarded是担保的意思，连在一起就是确保挂起
 * 当线程在访问某个对象某个对象的时候， 发现条件不足， 就暂时挂起等待条件满足时再次访问
 *
 *
 *
 * @author: Kevin
 * @date: created in 下午10:18 2018-11-06
 * @version: V1.0
 */
public class GuardedSuspensionQueue {

	// 存放Integer类型的queue
	private final LinkedList<Integer> queue = new LinkedList<Integer>();

	// 定义queue的最大容量为100
	private final int LIMIT = 100;

	// 往queue插入数据， 如果queue中的元素超过了最大容量， 则会陷入阻塞
	public void offer(Integer data){
		synchronized (this){
			// 判定queue的当前元素是否超过LIMIT
			while(queue.size()>=LIMIT){
				try {
					// 挂起当前线程， 使其陷入阻塞
					this.wait();
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
			// 插入元素并唤醒take线程
			queue.addLast(data);
			this.notifyAll();
		}
	}

	// 从队列中获取元素， 如果队列为空， 则会使当前线程阻塞
	public Integer take(){
		synchronized (this){
			while(queue.isEmpty()){
				try {
					// 挂起当前线程， 使其陷入阻塞
					this.wait();
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
			// 通知offer线程可以继续插入数据
			this.notifyAll();
			return queue.removeFirst();
		}
	}
}
