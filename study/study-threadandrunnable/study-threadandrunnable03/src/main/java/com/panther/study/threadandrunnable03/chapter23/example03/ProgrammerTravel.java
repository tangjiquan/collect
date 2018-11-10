package com.panther.study.threadandrunnable03.chapter23.example03;

import java.util.concurrent.ThreadLocalRandom;
import java.util.concurrent.TimeUnit;

/**
 * @author: Kevin
 * @date: created in 下午6:10 2018-11-10
 * @version: V1.0
 */
public class ProgrammerTravel extends Thread {
	private final Latch latch;

	private final String programmer;

	private final String transportation;

	public ProgrammerTravel(Latch latch, String programmer, String transportation){
		this.latch = latch;
		this.programmer = programmer;
		this.transportation = transportation;
	}

	@Override
	public void run() {
		try {
			TimeUnit.SECONDS.sleep(ThreadLocalRandom.current().nextInt(10));
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		System.out.println(programmer + " arrived by " + transportation);
		// 完成任务时使计数器减一
		latch.countDown();

	}
}
