package com.panther.study.threadandrunnable03.chapter23.example02;


import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

/**

 *
 * @author: Kevin
 * @date: created in 下午6:14 2018-11-10
 * @version: V1.0
 */
public class MainTest {

	public static void main(String[] args){
		Latch latch = new CountDownLatch(new AtomicInteger(4));
		new ProgrammerTravel(latch, "aa", "aa1").start();
		new ProgrammerTravel(latch, "bb", "aa2").start();
		new ProgrammerTravel(latch, "cc", "aa3").start();
		new ProgrammerTravel(latch, "dd", "aa4").start();
		try {
			latch.await(TimeUnit.SECONDS, 5);
		} catch (RuntimeException e) {
			e.printStackTrace();
		}
		System.out.println("== all of programmer arrived ==");

	}
}
