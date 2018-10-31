package com.panther.study.threadandrunnable03.chapter15.example01;

import java.util.concurrent.TimeUnit;

/**
 * @author: Kevin
 * @date: created in 下午9:59 2018-10-30
 * @version: V1.0
 */
public class MainTest {

	public static void main(String[] args){
		Observable observableThread = new ObservableThread<>(new Task<Object>() {
			@Override
			public Object call() {
				try {
					TimeUnit.SECONDS.sleep(3);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				System.out.println("finish done");
				return "ok";
			}
		});
		observableThread.start();
	}
}
