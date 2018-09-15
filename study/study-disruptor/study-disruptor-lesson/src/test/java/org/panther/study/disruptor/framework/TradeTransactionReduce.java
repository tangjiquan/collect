package org.panther.study.disruptor.framework;

import com.lmax.disruptor.EventHandler;

/**
 * @author: Kevin
 * @date: created in 下午1:30 2018-07-27
 * @version: V1.0
 */
public class TradeTransactionReduce implements EventHandler<TradeTransaction> {

	@Override
	public void onEvent(TradeTransaction event, long sequence, boolean endOfBatch) throws Exception {
	}
}
