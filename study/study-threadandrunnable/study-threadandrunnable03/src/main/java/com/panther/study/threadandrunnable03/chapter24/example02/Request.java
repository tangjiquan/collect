package com.panther.study.threadandrunnable03.chapter24.example02;

/**
 * @author: Kevin
 * @date: created in 下午8:06 2018-11-11
 * @version: V1.0
 */
public class Request {
	private final String business;

	public Request(String business){
		this.business = business;
	}

	@Override
	public String toString() {
		return business;
	}
}
