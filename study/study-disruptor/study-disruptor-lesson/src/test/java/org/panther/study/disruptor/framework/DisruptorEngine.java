package com.sinosoft.common.utils.makePoster;

import com.lmax.disruptor.BlockingWaitStrategy;
import com.lmax.disruptor.EventFactory;
import com.lmax.disruptor.YieldingWaitStrategy;
import com.lmax.disruptor.dsl.Disruptor;
import com.lmax.disruptor.dsl.EventHandlerGroup;
import com.lmax.disruptor.dsl.ProducerType;
import com.sinosoft.modules.weixin.vo.agent.CustomerInfoResponse;
import org.slf4j.LoggerFactory;

import java.util.*;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @author: Kevin
 * @date: created in 下午1:25 2018-07-27
 * @version: V1.0
 */
public class DisruptorEngine {

	static org.slf4j.Logger logger = LoggerFactory.getLogger(DisruptorEngine.class);

	public  void makePoster(int num,ArrayList sendList){

		long beginTime = System.currentTimeMillis();

		int bufferSize = 1024;

		final ArrayList list=sendList;



		ExecutorService executor= Executors.newFixedThreadPool(1000);

		Disruptor<TradeTransaction> disruptor=new Disruptor<TradeTransaction>(new EventFactory<TradeTransaction>() {
			@Override
			public TradeTransaction newInstance() {
				return new TradeTransaction(list);
			}
		}, bufferSize, executor, ProducerType.MULTI, new YieldingWaitStrategy());
		int ii = num;
		TradeTransactionMap[] arr = new TradeTransactionMap[ii];
		for(int j=0 ; j<ii; j++){
			arr[j] = new TradeTransactionMap();
			arr[j].setPostion(j);
		}

		EventHandlerGroup<TradeTransaction> handlerGroup=disruptor.handleEventsWith(arr);

		TradeTransactionReduce jmsConsumer=new TradeTransactionReduce();
		//声明在C1,C2完事之后执行JMS消息发送操作 也就是流程走到C3
		handlerGroup.then(jmsConsumer);

		disruptor.start();
		CountDownLatch latch = new CountDownLatch(1);
		executor.submit(new TradeTransactionPublisher(latch, disruptor));
		try {
			latch.await();//等待生产者完事.
		} catch (InterruptedException e) {
			e.printStackTrace();
		}


		disruptor.shutdown();
		executor.shutdown();

		long sequence = disruptor.getSequenceValueFor(jmsConsumer);
		TradeTransaction tradeTransaction = disruptor.get(sequence);
		//获取结果集
		ArrayList a=tradeTransaction.getOutputValues();
		if(a!=null&&a.size()>0){
			logger.info("=================================================================================实际发送："+String.valueOf(a.size())+"个模板消息============================");
		}
	}
}
