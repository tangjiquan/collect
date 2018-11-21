package com.panther.study.threadandrunnable03.chapter26.example01;

import java.util.Random;

/**
 * 流水线工人
 *
 * 不断的从流水线上提取产品后再次加工
 *
 * @author: Kevin
 * @date: created in 下午10:00 2018-11-21
 * @version: V1.0
 */
public class Worker extends  Thread{

	private final ProductionChannel channel;

	private final static Random random = new Random(System.currentTimeMillis());
	public Worker(String workerName, ProductionChannel channel){
		super(workerName);
		this.channel = channel;
	}

	@Override
	public void run() {
		while (true) {
			try{
				// 从传送带上获取产品
				Production production = channel.takeProduction();
				System.out.println(getName() + " process the " + production);
				// 对产品进行加工
				production.create();
			}catch (Exception e){
				e.printStackTrace();
			}

		}
	}
}
