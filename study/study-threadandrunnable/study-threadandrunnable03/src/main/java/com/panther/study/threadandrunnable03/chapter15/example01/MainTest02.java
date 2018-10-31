package com.panther.study.threadandrunnable03.chapter15.example01;

import java.util.concurrent.TimeUnit;

/**
 * @author: Kevin
 * @date: created in 下午9:59 2018-10-30
 * @version: V1.0
 */
public class MainTest02 {

	public static void main(String[] args){
		final TaskLifecycle<String> lifecycle = new TaskLifecycle.EmptyLifecycle<String>(){
			@Override
			public void onFinish(Thread thread, String result) {
				System.out.println("this result done");
			}
		};
		Observable observableThread = new ObservableThread<>(lifecycle, new Task<String>() {
			@Override
			public String call() {
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
