package com.panther.study.threadandrunnable03.chapter16.example01;

/**
 * @author: Kevin
 * @date: created in 下午10:41 2018-10-31
 * @version: V1.0
 */
public class Passengers extends Thread{

	// 安检类
	private final FlightSecurity flightSecurity;

	// 旅客的身份证
	private final String idCard;

	private final String boardingPass;

	public Passengers(FlightSecurity flightSecurity, String idCard, String boardingPass){
		this.flightSecurity = flightSecurity;
		this.idCard = idCard;
		this.boardingPass = boardingPass;
	}

	@Override
	public void run() {

		while(true){
			flightSecurity.pass(boardingPass, idCard);
		}
	}

	public static void main(String[] args){
		final FlightSecurity flightSecurity = new FlightSecurity();
		new Passengers(flightSecurity, "AAA", "AAA1").start();
		new Passengers(flightSecurity, "BBB", "BBB1").start();
		//new Passengers(flightSecurity, "BBB", "CCC1").start();

	}
}
