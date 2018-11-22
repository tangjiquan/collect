package com.panther.study.threadandrunnable04.chapter01.example02;

/**
 * 执行结果：
	 The main method was executed by thread:main
	 The dosomething method was exectued by thread :Thread-0
	 Do something with java thread

 * @author: Kevin
 * @date: created in 下午9:44 2018-11-22
 * @version: V1.0
 */
public class JavaThreadAnyWhere {
	public static void main(String[] args){
		System.out.println("The main method was executed by thread:" + Thread.currentThread().getName());
		Helper helper = new Helper("java thread");
		new Thread(helper).start();
	}

	static class Helper implements Runnable{
		private String msg;

		public Helper(String msg){
			this.msg = msg;
		}
		@Override
		public void run() {
			doSomething(msg);
		}

		private void doSomething(String msg){
			System.out.println("The dosomething method was exectued by thread :" + Thread.currentThread().getName());
			System.out.println("Do something with " + msg);
		}
	}
}
