package com.panther.study.designpattern.example04.test02;

import java.util.ArrayList;
import java.util.List;

/**
 * @author: Kevin
 * @date: created in 下午6:22 2018-09-19
 * @version: V1.0
 */
public class MainTest {

	public static void main(String[] args){
		LogModel lm1 = new LogModel();
		lm1.setLogId("1");
		lm1.setLongContent("测试");
		lm1.setOperateTime("2018-09-11");
		List<LogModel> list = new ArrayList<LogModel>();
		list.add(lm1);

		// 写入日志文件
		LogFileOperateApi api = new LogFileOperate();
		api.writeLogFile(list);

		// 读出日志文件
		List<LogModel> result = api.readFile();

	}
}
