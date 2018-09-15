package com.sinosoft.common.utils.makePoster;

import com.lmax.disruptor.EventTranslator;

import java.util.Random;

/**
 * @author: Kevin
 * @date: created in 下午1:30 2018-07-27
 * @version: V1.0
 */

public class TradeTransactionEventTranslator  implements EventTranslator<TradeTransaction> {

	private Random random=new Random();
	@Override
	public void translateTo(TradeTransaction event, long sequence) {
		this.generateTradeTransaction(event);
	}
	private TradeTransaction generateTradeTransaction(TradeTransaction trade){
		return trade;
	}

}
