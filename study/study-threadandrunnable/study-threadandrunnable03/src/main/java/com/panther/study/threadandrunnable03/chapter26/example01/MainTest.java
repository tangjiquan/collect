package com.panther.study.threadandrunnable03.chapter26.example01;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * worker-Thread模式（流水线模式）
 *
 *
 *
 *
 * 假设上游的流水线有8个工人将产品放到传送带上， 我们在传送带上定义了5个工人
 * @author: Kevin
 * @date: created in 下午10:10 2018-11-21
 * @version: V1.0
 */
public class MainTest {

	public static void main(String[] args){
		// 流水线上有5个工人
		final ProductionChannel channel = new ProductionChannel(5);

		final AtomicInteger productionNo = new AtomicInteger();
		for(int i=0; i<8; i++){
			new Thread(new Runnable() {
				@Override
				public void run() {
					while(true){
						channel.offerProduction(new Production(productionNo.getAndIncrement()));
						try {
							TimeUnit.SECONDS.sleep(1);
						} catch (InterruptedException e) {
							e.printStackTrace();
						}
					}
				}
			}).start();
		}
	}
}
