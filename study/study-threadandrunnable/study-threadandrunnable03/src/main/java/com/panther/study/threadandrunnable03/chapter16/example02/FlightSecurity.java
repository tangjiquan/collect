package com.panther.study.threadandrunnable03.chapter16.example02;

/**
 *
 * 给pass方法增加了synchronized方法， 线程就安全了
 *
 * check方法是在pass方法中执行， pass方法已经加同步保证了single thread execution, 因此check方法不需要增加同步
 * toString是同样的原理
 *
 * @author: Kevin
 * @date: created in 下午10:35 2018-10-31
 * @version: V1.0
 */
public class FlightSecurity {

	private int count = 0;

	// 登记牌
	private String boardingPass = "null";
	// 身份证
	private String idCard = "null";

	public synchronized void pass(String boardingPass, String idCard){
		this.boardingPass = boardingPass;
		this.idCard = idCard;
		this.count++;
		check();
	}

	private void check(){
		if(boardingPass.charAt(0)!=idCard.charAt(0)){
			throw new RuntimeException("====== Exception ==========" + toString());
		}
	}

	@Override
	public String toString(){
		return "the " + count + " passengers, boardingPass[" + boardingPass +"], idCard" + idCard +"]";

	}
}
