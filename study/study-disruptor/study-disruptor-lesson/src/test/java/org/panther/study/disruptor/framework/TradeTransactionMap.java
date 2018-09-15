package org.panther.study.disruptor.framework;

import com.lmax.disruptor.EventHandler;
import org.panther.study.disruptor.ImageUtil6;

/**
 * @author: Kevin
 * @date: created in 下午1:30 2018-07-27
 * @version: V1.0
 */
public class TradeTransactionMap implements EventHandler<TradeTransaction> {
	private Integer postion;

	public void setPostion(Integer postion){
		this.postion = postion;
	}
	@Override
	public void onEvent(TradeTransaction event, long sequence,
                        boolean endOfBatch) throws Exception {
			ImageUtil6 util6=new ImageUtil6();
			//util6.exec();
	}

}
