package com.panther.study.designpattern.example04.test02;

/**
 * 日志对象
 *
 * @author: Kevin
 * @date: created in 下午6:11 2018-09-19
 * @version: V1.0
 */
public class LogModel {
	private String logId;
	private String operateUser;
	private String operateTime;
	private String longContent;

	public String getLogId() {
		return logId;
	}

	public void setLogId(String logId) {
		this.logId = logId;
	}

	public String getOperateUser() {
		return operateUser;
	}

	public void setOperateUser(String operateUser) {
		this.operateUser = operateUser;
	}

	public String getOperateTime() {
		return operateTime;
	}

	public void setOperateTime(String operateTime) {
		this.operateTime = operateTime;
	}

	public String getLongContent() {
		return longContent;
	}

	public void setLongContent(String longContent) {
		this.longContent = longContent;
	}
}
