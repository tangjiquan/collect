package org.panther.study.threadandrunnable02.example12;

/**
 * 多线程中最好用， 最安全的单例模式
 *
 * @author: Kevin
 * @date: created in 下午6:09 2018-09-09
 * @version: V1.0
 */
public class InnerSingleton {

	private static class Singleton{
		private static Singleton singleton = new Singleton();
	}

	public static Singleton getInstance(){
		return Singleton.singleton;
	}
}
