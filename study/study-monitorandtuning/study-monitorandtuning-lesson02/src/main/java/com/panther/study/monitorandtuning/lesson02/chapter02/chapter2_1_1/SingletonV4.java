package com.panther.study.monitorandtuning.lesson02.chapter02.chapter2_1_1;

/**
 * 使用内部类进行创建单例模式
 *
 * @author: Kevin
 * @date: created in 下午9:34 2019-07-02
 * @version: V1.0
 */
public class SingletonV4 {

	private SingletonV4(){
		System.out.println("SingletonV4 is create");
	}


	private static class SingletonHolder{
		private static SingletonV4 instance = new SingletonV4();
	}
	public static  SingletonV4 getInstance(){
		return SingletonHolder.instance;
	}

	public static void main(String[] args){
		SingletonV4.getInstance();
	}
}
