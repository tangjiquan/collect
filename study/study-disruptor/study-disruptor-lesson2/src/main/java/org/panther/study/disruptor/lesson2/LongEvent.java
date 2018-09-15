package org.panther.study.disruptor.lesson2;

/**
 * @author: Kevin
 * @date: created in 上午11:41 2018-09-04
 * @version: V1.0
 */
public class LongEvent {
	private long value;
	public long getValue() {
		return value;
	}

	public void setValue(long value) {
		this.value = value;
	}
}
