package com.sinosoft.common.utils.makePoster;

import java.util.ArrayList;

/**
 * @author: Kevin
 * @date: created in 下午1:29 2018-07-27
 * @version: V1.0
 */
public class TradeTransaction<T> {
	private ArrayList<T> inputValues;
	private ArrayList<T> outputValues;
	public TradeTransaction(ArrayList list ){
		this.inputValues=list;
	}
	public TradeTransaction(){
	}

	public TradeTransaction(String id,double price){
		super();
	}

	public ArrayList<T> getInputValues() {
		return inputValues;
	}

	public void setInputValues(ArrayList<T> inputValues) {
		this.inputValues = inputValues;
	}

	public ArrayList<T> getOutputValues() {
		return outputValues;
	}

	public void setOutputValues(ArrayList<T> outputValues) {
		this.outputValues = outputValues;
	}
}
