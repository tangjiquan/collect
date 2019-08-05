package com.panther.study.monitorandtuning.lesson02.chapter02.chapter2_1_1;

/**
 * @author: Kevin
 * @date: created in 下午9:34 2019-07-02
 * @version: V1.0
 */
public class SingletonV2 {

	private SingletonV2(){
		System.out.println("SingletonV1 is create");
	}

	public static SingletonV2 instance = new SingletonV2();


	public static SingletonV2 getInstance(){
		return instance;
	}


	public static void createString(){
		System.out.println("createString is Singletion ");

	}


	public static void main(String[] args){


		/**
		 * 当使用Singleton.createString()的时候，输出：
		 * SingletonV1 is create
		 * createString is Singletion
		 * 虽然没有使用单例， 但是还是被创建出来了
		 *
		 *
		 */
		SingletonV2.createString();
	}

}
