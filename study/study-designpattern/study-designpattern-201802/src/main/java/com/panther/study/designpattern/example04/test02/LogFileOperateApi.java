package com.panther.study.designpattern.example04.test02;

import java.util.List;

/**
 * 日志文件操作接口
 *
 * @author: Kevin
 * @date: created in 下午6:16 2018-09-19
 * @version: V1.0
 */
public interface LogFileOperateApi {

	/**
	 * 读取日志文件， 从文件里读取
	 * @return
	 */
	public List<LogModel> readFile();

	/**
	 * 写入文件， 把日志写到日志文件中去
	 *
	 * @param list
	 */
	public void writeLogFile(List<LogModel> list);
}
