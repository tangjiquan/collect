package com.panther.study.threadandrunnable03.chapter23.example01;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * Latch（门阀）模式
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
		latch.await();
		System.out.println("== all of programmer arrived ==");

	}
}
