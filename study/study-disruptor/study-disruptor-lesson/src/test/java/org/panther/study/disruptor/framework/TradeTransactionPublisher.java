package org.panther.study.disruptor.framework;

import com.lmax.disruptor.dsl.Disruptor;


import java.util.concurrent.CountDownLatch;

/**
 * @author: Kevin
 * @date: created in 下午1:31 2018-07-27
 * @version: V1.0
 */
public class TradeTransactionPublisher implements Runnable{
	Disruptor<TradeTransaction> disruptor;
	private CountDownLatch latch;
	private static int LOOP=1;//模拟一千万次交易的发生

	public TradeTransactionPublisher(CountDownLatch latch, Disruptor<TradeTransaction> disruptor){
		this.disruptor = disruptor;
		this.latch = latch;
	}
	@Override
	public void run() {
		TradeTransactionEventTranslator tradeTransloator=new TradeTransactionEventTranslator();
		//for(int i=0;i<LOOP;i++){

			disruptor.publishEvent(tradeTransloator);
		//}
		latch.countDown();
	}
}
