package com.panther.study.threadandrunnable03.chapter14.example01;

/**
 * Double - Check
 * 首次初始化时加锁，之后则允许多个线程同时进行getInstance方法调用来获取类的实例
 *
 * 这种方式在多线程情况下会引起空指针异常
 *
 * @author: Kevin
 * @date: created in 下午8:20 2018-10-28
 * @version: V1.0
 */
public final class Singleton04 {
	private byte[] data = new byte[1024];

	private static Singleton04 instance = null;

	private Singleton04(){}

	public static  Singleton04 getInstance(){
		// 当instace为null时候，进入同步代码块，同时该判定避免每次都需要进入同步代码块，提交效率
		if(null == instance){
			// 只有一个线程能够获得Singleton.class管理的monitor
			synchronized (Singleton04.class){
				// 判定如果instace为null则创建
				if(null == instance){
					instance = new Singleton04();
				}
			}

		}
		return instance;
	}

}
