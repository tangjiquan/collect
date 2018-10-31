package com.panther.study.threadandrunnable03.chapter16.example04;

/**
 * @author: Kevin
 * @date: created in 下午10:52 2018-10-31
 * @version: V1.0
 */
public class TableWare {

	private final String toolName;

	public TableWare(String toolName){
		this.toolName = toolName;

	}

	@Override
	public String toString(){
		return "tool" + toolName;
	}


}
