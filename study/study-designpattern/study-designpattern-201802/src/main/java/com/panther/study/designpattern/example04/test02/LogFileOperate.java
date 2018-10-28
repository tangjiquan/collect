package com.panther.study.designpattern.example04.test02;

import java.util.List;

/**
 * @author: Kevin
 * @date: created in 下午6:18 2018-09-19
 * @version: V1.0
 */
public class LogFileOperate implements LogFileOperateApi {
	@Override
	public List<LogModel> readFile() {
		System.out.println("读出日志");

		return null;
	}

	@Override
	public void writeLogFile(List<LogModel> list) {
		System.out.println("写入日志");
	}
}
