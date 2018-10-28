package com.panther.study.designpattern.example02.test02;

/**
 * @author: Kevin
 * @date: created in 上午11:05 2018-09-16
 * @version: V1.0
 */
public class Factory {

	public static Api createApi(String type){
		/**
		 * 简单工厂方法内部主要实现功能是“选择合适的实现类“
		 * 1. 来源客户端， 由client传入
		 * 2. 来源配置文件， 有配置文件传入
		 * 3. 来源程序运行中的某个值， 比如从缓存中获取某个运行的值
		 *
		 */
		if("1".equals(type)){
			return new ApiImpl1();
		}else {
			return new ApiImpl2();
		}

	}
}
