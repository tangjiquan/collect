package com.panther.study.dubbo.lesson01.chapter05.dirct.provider.impl;

import com.panther.study.dubbo.lesson01.chapter05.dirct.provider.DirectService;

/**
 * @author: Kevin
 * @date: created in 下午4:42 2018-10-03
 * @version: V1.0
 */
public class DirectServiceImpl implements DirectService{


	@Override
	public String direct() {
		return "direct service";
	}
}
