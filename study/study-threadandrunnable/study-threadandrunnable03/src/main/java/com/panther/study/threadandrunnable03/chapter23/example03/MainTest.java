package com.panther.study.threadandrunnable03.chapter23.example03;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * 对latch进行再次扩展， 增肌回调接口用于运行所有子任务完成后的其他任务
 * 增加回调功能
 *
 * @author: Kevin
 * @date: created in 下午6:14 2018-11-10
 * @version: V1.0
 */
public class MainTest {

	public static void main(String[] args) {
		Latch latch = new CountDownLatch(new AtomicInteger(4), new Runnable() {
			@Override
			public void run() {
				System.out.println("Runnbale ...");
			}
		});
		new ProgrammerTravel(latch, "aa", "aa1").start();
		new ProgrammerTravel(latch, "bb", "aa2").start();
		new ProgrammerTravel(latch, "cc", "aa3").start();
		new ProgrammerTravel(latch, "dd", "aa4").start();
		latch.await();
		System.out.println("== all of programmer arrived ==");

	}
}
