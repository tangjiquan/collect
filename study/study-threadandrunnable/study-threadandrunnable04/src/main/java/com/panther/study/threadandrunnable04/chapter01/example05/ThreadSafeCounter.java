package com.panther.study.threadandrunnable04.chapter01.example05;

/**
 * 线程安全的计数器
 *
 * @author: Kevin
 * @date: created in 下午10:42 2018-11-22
 * @version: V1.0
 */
public class ThreadSafeCounter {

	private int count = 0;

	public void increment(){
		synchronized (this){
			count++;
		}
	}

	public int get(){
		synchronized (this){
			return count;
		}
	}
}
