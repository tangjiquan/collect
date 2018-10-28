package com.panther.study.threadandrunnable03.chapter14.example01;

/**
 * 懒汉式+同步实现单例模式
 *
 * 采用 懒汉式+同步实现单例模式 可以实现懒加载又能百分百保证instance实例的唯一性，但是synchronized关键字
 * 天生的排他性导致了getInstace方法只能被同一个时刻被一个线程访问， 性能低下
 *
 * @author: Kevin
 * @date: created in 下午8:20 2018-10-28
 * @version: V1.0
 */
public final class Singleton03 {
	private byte[] data = new byte[1024];

	private static Singleton03 instance = null;

	private Singleton03(){}

	// 向getInstace 方法加入同步控制， 每次只能有一个线程能够进入
	public static synchronized Singleton03 getInstance(){
		if(null == instance){
			instance = new Singleton03();
		}
		return instance;
	}

}
