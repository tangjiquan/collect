package com.panther.study.designpattern.example04.test02;

import java.util.List;

/**
 * @author: Kevin
 * @date: created in 下午6:42 2018-09-19
 * @version: V1.0
 */
public class Adapter implements LogDbOperateApi {

	private LogFileOperateApi apaptee;

	public Adapter(LogFileOperateApi apaptee){
		this.apaptee = apaptee;
	}
	@Override
	public void createLog(LogModel l) {
		List<LogModel> list = apaptee.readFile();
		list.add(l);
		apaptee.writeLogFile(list);
	}

	@Override
	public void updateLog(LogModel l) {

	}

	@Override
	public void removeLog(LogModel l) {

	}

	@Override
	public void getLog(LogModel l) {
		apaptee.readFile();
	}
}
