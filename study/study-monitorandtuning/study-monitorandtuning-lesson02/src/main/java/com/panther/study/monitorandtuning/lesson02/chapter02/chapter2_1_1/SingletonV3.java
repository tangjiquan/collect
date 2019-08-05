package com.panther.study.monitorandtuning.lesson02.chapter02.chapter2_1_1;

/**
 * 懒加载
 *
 * @author: Kevin
 * @date: created in 下午9:34 2019-07-02
 * @version: V1.0
 */
public class SingletonV3 {

	private SingletonV3(){
		System.out.println("SingletonV3 is create");
	}

	public static SingletonV3 instance = null;


	public static synchronized SingletonV3 getInstance(){
		if(instance == null){
			return instance;
		}
		return instance;
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
		SingletonV3.getInstance();
	}

}
