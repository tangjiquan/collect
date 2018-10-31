package com.panther.study.threadandrunnable03.chapter16.example04;

/**
 * @author: Kevin
 * @date: created in 下午11:05 2018-10-31
 * @version: V1.0
 */
public class TableWarePair {

	private final TableWare leftTool;
	private final TableWare rightTool;

	public TableWarePair(TableWare leftTool, TableWare rightTool){
		this.leftTool = leftTool;
		this.rightTool = rightTool;
	}

	public TableWare getLeftTool() {
		return leftTool;
	}

	public TableWare getRightTool() {
		return rightTool;
	}
}
