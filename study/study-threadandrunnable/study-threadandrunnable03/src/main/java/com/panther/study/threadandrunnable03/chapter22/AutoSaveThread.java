package com.panther.study.threadandrunnable03.chapter22;

import java.util.concurrent.TimeUnit;

/**
 * @author: Kevin
 * @date: created in 下午8:54 2018-11-08
 * @version: V1.0
 */
public class AutoSaveThread extends Thread{

	private final Document document;

	public AutoSaveThread(Document document){
		this.document = document;
	}

	@Override
	public void run() {
		while(true){
			document.save();
			try {
				TimeUnit.SECONDS.sleep(1);
			} catch (InterruptedException e) {
				e.printStackTrace();
				break;
			}
		}
	}
}
