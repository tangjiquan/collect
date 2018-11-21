package com.panther.study.threadandrunnable03.chapter26.example01;

/**
 * 产品信息
 *
 * @author: Kevin
 * @date: created in 下午9:50 2018-11-21
 * @version: V1.0
 */
public class Production extends InstructionBook{

	// 产品编号
	private final int prodID;
	public Production(int prodID){
		this.prodID = prodID;

	}

	@Override
	protected void firstProcess() {
		System.out.println("execute the " + prodID + " first process");
	}

	@Override
	protected void secondProcess() {
		System.out.println("execute the " + prodID + " second process");
	}
}
