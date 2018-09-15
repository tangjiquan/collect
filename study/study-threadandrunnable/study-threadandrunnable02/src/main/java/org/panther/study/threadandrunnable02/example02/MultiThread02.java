package org.panther.study.threadandrunnable02.example02;

/**
 * 在方法上加上static后， 不管是多个线程操作不同对象， 都是一个一个的排队执行
 * 在静态方法上加上synchronized，那么这个是类级别的锁
 * @author: Kevin
 * @date: created in 下午1:33 2018-08-26
 * @version: V1.0
 */
public class MultiThread02 {
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
		final MultiThread02 m1 = new MultiThread02();
		final MultiThread02 m2 = new MultiThread02();

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
