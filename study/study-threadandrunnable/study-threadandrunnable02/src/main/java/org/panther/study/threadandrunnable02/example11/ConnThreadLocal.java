package org.panther.study.threadandrunnable02.example11;

/**
 * @author: Kevin
 * @date: created in 下午5:53 2018-09-09
 * @version: V1.0
 */
public class ConnThreadLocal {

	public static ThreadLocal<String> th = new ThreadLocal<String>();

	public void setTh(String value){
		th.set(value);
	}

	public void getTh(){
		System.out.println(Thread.currentThread().getName() + ": " + th.get());
	}

	public static void main(String[] args){
		final ConnThreadLocal ct = new ConnThreadLocal();
		Thread t1 = new Thread(new Runnable() {
			@Override
			public void run() {
				ct.setTh("张三");
				ct.getTh();
			}
		}, "t1");

		Thread t2 = new Thread(new Runnable() {
			@Override
			public void run() {
				try {
					Thread.sleep(1000);
					ct.setTh("lisi");
					ct.getTh();
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		}, "t2");

		t1.start();
		t2.start();

	}
}
