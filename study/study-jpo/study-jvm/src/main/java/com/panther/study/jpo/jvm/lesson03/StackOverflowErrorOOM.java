package com.panther.study.jpo.jvm.lesson03;

/**
 * @author: Kevin
 * @date: created in 下午10:37 2019-02-14
 * @version: V1.0
 */
public class StackOverflowErrorOOM {

	public static void main(String[] args){
		int count = 0;
		while(true){
			Thread thread = new Thread(new Runnable() {
				@Override
				public void run() {
					try {
						Thread.sleep(5000);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
			});
			thread.start();
			System.out.println(count++);
		}
	}


}
