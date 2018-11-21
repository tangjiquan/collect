package com.panther.study.threadandrunnable03.chapter26.example01;

/**
 * 流水线传送带
 *
 * @author: Kevin
 * @date: created in 下午9:53 2018-11-21
 * @version: V1.0
 */
public class ProductionChannel {

	// 传送带上最多可以有多少个待加工的产品
	private final static int MAX_PROD = 100;

	// 主要用来处理代加工的产品，
	private final Production[] productionQueue;

	// 队列尾部
	private int tail;
	// 队列头
	private int head;

	// 流水线上有多少个代加工的产品
	private int total;

	// 流水线上的工人
	private final Worker[] workers;


	public ProductionChannel(int workerSize){
		this.workers = new Worker[workerSize];
		this.productionQueue = new Production[MAX_PROD];
		for (int i=0; i<workerSize; i++){
			workers[i] = new Worker("worker-" + i, this);
			workers[i].start();
		}
	}

	public void offerProduction(Production production){
		synchronized (this){
			// 当传送带上的待加工的产品超过最大值时，需要阻塞上游再次传送的产品
			while(total >= productionQueue.length){
				try {
					this.wait();
				} catch (InterruptedException e) {

				}
				// 将产品放到传送带上， 并通知工人线程工作
				productionQueue[tail] = production;
				tail = (tail+1) % productionQueue.length;
				total++;
				this.notifyAll();
			}
		}
	}

	// 工人线程从传送带上获取产品，并进行加工
	public Production takeProduction(){
		synchronized (this){
			while(total<=0){
				try {
					this.wait();
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
			Production production = productionQueue[head];
			head = (head + 1) % productionQueue.length;
			total--;
			this.notifyAll();
			return production;
		}
	}











}
