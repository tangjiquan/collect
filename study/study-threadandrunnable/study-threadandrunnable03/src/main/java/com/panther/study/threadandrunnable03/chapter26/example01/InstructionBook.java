package com.panther.study.threadandrunnable03.chapter26.example01;

/**
 * 产品及组装说明
 * 在流水线上需要加工的产品
 *
 * @author: Kevin
 * @date: created in 下午9:47 2018-11-21
 * @version: V1.0
 */
public abstract class InstructionBook {

	public final void create(){
		this.firstProcess();
		this.secondProcess();
	}
	protected abstract void firstProcess();
	protected abstract void secondProcess();



}
