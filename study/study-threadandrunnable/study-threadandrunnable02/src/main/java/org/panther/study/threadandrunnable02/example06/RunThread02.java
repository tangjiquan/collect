package org.panther.study.threadandrunnable02.example06;

/**
 * 想让执行死循环的run方法一直在执行， 3秒后讲isRunning设置为false后， 死循环会停止
 * @author: Kevin
 * @date: created in 下午11:27 2018-09-05
 * @version: V1.0
 */
public class RunThread extends Thread{

	private boolean isRunning = true;
	private void setRunning(boolean isRunning){
		this.isRunning = isRunning;
	}

	@Override
	public void run() {
		System.out.println("进入run方法");
		while(isRunning == true){

		}
		System.out.println("线程终止");
	}

	public static void main(String[] args) throws InterruptedException {
		RunThread rt = new RunThread();
		rt.start();
		Thread.sleep(3000);
		rt.setRunning(false);
		System.out.println("isRunning已经设置为false");
		Thread.sleep(1000);
		System.out.println(rt.isRunning);
	}
}
