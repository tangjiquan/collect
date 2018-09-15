package org.panther.study.disruptor.lesson2;

import com.lmax.disruptor.EventFactory;

/**
 * @author: Kevin
 * @date: created in 上午11:42 2018-09-04
 * @version: V1.0
 */
public class LongEventFactory implements EventFactory{
	@Override
	public Object newInstance() {
		return new LongEvent();
	}
}
