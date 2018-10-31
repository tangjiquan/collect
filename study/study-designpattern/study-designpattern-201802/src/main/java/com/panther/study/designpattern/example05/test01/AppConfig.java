package com.panther.study.designpattern.example05.test01;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 * 不用设计模式读取配置文件
 *
 * @author: Kevin
 * @date: created in 下午3:33 2018-10-31
 * @version: V1.0
 */
public class AppConfig {

	private String parameterA;
	private String parameterB;

	public String getParameterA(){
		return parameterA;
	}

	public String getParameterB(){
		return parameterB;
	}

	public AppConfig(){
		// 调用读取配置文件的方法
		readConfig();
	}

	private void readConfig() {

		Properties p = new Properties();
		InputStream in = null;

		in = AppConfig.class.getResourceAsStream("/config/AppConfig.properties");

		try {
			p.load(in);
			this.parameterA = p.getProperty("paramA");
			this.parameterB = p.getProperty("paramB");

		} catch (IOException e) {
			e.printStackTrace();
		}finally {
			try {
				in.close();
			} catch (IOException e) {
				e.printStackTrace();
			}

		}


	}
}
