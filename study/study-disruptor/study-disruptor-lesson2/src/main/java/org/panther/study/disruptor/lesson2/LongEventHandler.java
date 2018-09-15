package org.panther.study.disruptor.lesson2;

import com.lmax.disruptor.EventHandler;

/**
 * 消费者
 *
 * @author: Kevin
 * @date: created in 上午11:43 2018-09-04
 * @version: V1.0
 */
public class LongEventHandler implements EventHandler<LongEvent> {


	@Override
	public void onEvent(LongEvent longEvent, long l, boolean b) throws Exception {
		System.out.println(longEvent.getValue());
	}
}
