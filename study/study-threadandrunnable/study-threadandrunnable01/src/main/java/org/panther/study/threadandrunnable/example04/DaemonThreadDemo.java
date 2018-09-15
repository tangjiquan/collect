package org.panther.study.threadandrunnable.example04;

/**
 * @author: Kevin
 * @date: created in 下午4:23 2018-07-08
 * @version: V1.0
 */

class DaemonThread implements Runnable{

	@Override
	public void run() {
		System.out.println("进入守护线程" + Thread.currentThread().getName());
		writeToFile();
		System.out.println("退出守护线程" + Thread.currentThread().getName());

	}

	private void writeToFile() {


	}
}
public class DaemonThreadDemo {




}
