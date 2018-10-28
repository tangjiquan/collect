package com.panther.study.threadandrunnable03.chapter14.example01;

/**
 * 懒汉式是在使用实例的时候再去创建，避免在类的初始化时提前创建
 *
 *
 * @author: Kevin
 * @date: created in 下午8:14 2018-10-28
 * @version: V1.0
 */
public final class Singleton02 {

	private byte[] data = new byte[1024];

	// 定义实例，但是不直接初始化
	private static Singleton02 instance = null;

	private Singleton02(){}

	/**
	 * getInstance()方法在多线程环境下会导致instance被实例化一次以上
	 *
	 * @return
	 */
	public static Singleton02 getInstance(){
		if(null == instance){
			instance = new Singleton02();
		}
		return instance;
	}
}
