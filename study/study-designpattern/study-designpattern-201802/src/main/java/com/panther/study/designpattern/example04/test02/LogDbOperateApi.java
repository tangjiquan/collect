package com.panther.study.designpattern.example04.test02;

/**
 * 日志数据库操作接口
 *
 * @author: Kevin
 * @date: created in 下午6:16 2018-09-19
 * @version: V1.0
 */
public interface LogDbOperateApi {

	/**
	 * 新增日志
	 * @param l
	 */
	public void createLog(LogModel l);


	public void updateLog(LogModel l);

	public void removeLog(LogModel l);

	public void getLog(LogModel l);

}
