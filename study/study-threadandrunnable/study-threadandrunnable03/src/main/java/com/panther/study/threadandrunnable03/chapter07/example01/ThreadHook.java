package com.panther.study.threadandrunnable03.chapter07.example01;

import java.util.concurrent.TimeUnit;

/**
 * jvm进程的退出是由于jvm进程中没有活跃的非守护线程， 或者收到了系统中中断信号， 向jvm程序中注入一个hook线程， 在jvm进程退出的时候， hook线程
 * 会启动， 荣光runtime可以向jvm注入多个hook线程
 * @author: Kevin
 * @date: created in 下午9:56 2018-10-20
 * @version: V1.0
 */
public class ThreadHook {
	public static void main(String[] args){
		Runtime.getRuntime().addShutdownHook(new Thread(){
			@Override
			public void run() {
				System.out.println("the hook thread 1 is running");
				try {
					TimeUnit.SECONDS.sleep(1);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				System.out.println("the hook thread 1 will exit");
			}
		});


		// 钩子线程可以是多个
		Runtime.getRuntime().addShutdownHook(new Thread(){
			@Override
			public void run() {
				System.out.println("the hook thread 2 is running");
				try {
					TimeUnit.SECONDS.sleep(1);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				System.out.println("the hook thread 2 will exit");
			}
		});

		System.out.println("the program will is stopping");
	}

}
