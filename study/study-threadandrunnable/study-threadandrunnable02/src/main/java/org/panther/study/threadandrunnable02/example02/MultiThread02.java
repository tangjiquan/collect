package org.panther.study.threadandrunnable02.example02;

/**
 * 一个对象一把锁
 * 两个对象有两个锁， 所以自己执行自己
 *
 * @author: Kevin
 * @date: created in 下午1:33 2018-08-26
 * @version: V1.0
 */
public class MultiThread01 {
	private static int num = 0;

	public static synchronized  void printNum(String tag){
		try{
			if(tag.equals("a")){
				num = 100;
				System.out.println("tag a  set num over");
			}else{
				num = 200;
				System.out.println("tag b  set num over");
			}
			System.out.println("tag :" + tag + " num : " + num );
		}catch (Exception e){

		}
	}

	public static void main(String[] args){
		final MultiThread01 m1 = new MultiThread01();
		final MultiThread01 m2 = new MultiThread01();

		Thread t1 = new Thread(new Runnable() {
			@Override
			public void run() {
				m1.printNum("a");
			}
		});

		Thread t2 = new Thread(new Runnable() {
			@Override
			public void run() {
				m1.printNum("b");
			}
		});

		t1.start();
		t2.start();
	}
}
