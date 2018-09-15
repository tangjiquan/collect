package org.panther.study.threadandrunnable02.example05;

/**
 * synchronized的重入
 * @author: Kevin
 * @date: created in 下午3:56 2018-08-26
 * @version: V1.0
 */
public class SyncDubbo1 {


	public synchronized  void method1(){
		System.out.println("method01");
		method2();
	}

	public synchronized  void method2(){
		System.out.println("method02");
		method3();
	}

	public synchronized  void method3(){
		System.out.println("method03");
	}

	public static void main(String[] args){
		final SyncDubbo1 syncDubbo1 = new SyncDubbo1();
		Thread t1 = new Thread(new Runnable() {
			@Override
			public void run() {
				syncDubbo1.method1();
			}
		});
		t1.start();
	}


}
