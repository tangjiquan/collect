package org.panther.study.threadandrunnable02.example09;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CountDownLatch;


/**
 *
 * t1线程一直在运行， t2一直监听t1线程， 当size为5的时候t2线程关闭
 * 使用countDownLatch可以让t2线程实时的接收到通知， 并且进行处理
 *
 * @author: Kevin
 * @date: created in 上午11:23 2018-09-09
 * @version: V1.0
 */
public class ListAdd103 {
	private volatile static List list = new ArrayList();

	public void add(){
		list.add("bjsxt");
	}

	public int size(){
		return list.size();
	}

	public static void main(String[] args){
		final ListAdd103 listAdd1 = new ListAdd103();

		//final Object lock = new Object();
		final CountDownLatch countDownLatch = new CountDownLatch(1);
		// new CountDownLatch(1)中的1代表发几次countDown()通知， 这样await（）才能接收到通知
		//final CountDownLatch countDownLatch = new CountDownLatch(1);

		Thread t1 = new Thread(new Runnable() {
			@Override
			public void run() {
				//synchronized (lock){
					for(int i = 0; i<10; i++){
						listAdd1.add();
						System.out.println("当前线程"+ Thread.currentThread().getName() + "添加了一个元素");
						try {
							Thread.sleep(500);
							if(listAdd1.size() == 5){
								System.out.println("已发出通知");
								//lock.notify();//不释放锁
								countDownLatch.countDown();
							}
						} catch (InterruptedException e) {
							e.printStackTrace();
						}
					}
				//}

			}
		}, "t1");

		Thread t2 = new Thread(new Runnable() {
			@Override
			public void run() {
				//synchronized (lock){
					if(listAdd1.size() != 5){
						try {
				//			lock.wait();//wait()释放锁
							countDownLatch.await();
						} catch (InterruptedException e) {
							e.printStackTrace();
						}
						System.out.println("当前线程接收到通知" + Thread.currentThread().getName() + "list.size = 5线程停止");
						throw  new RuntimeException();
					}
				//}
			}
		}, "t2");

		// t2必须先执行
		t2.start();
		t1.start();

	}
}
