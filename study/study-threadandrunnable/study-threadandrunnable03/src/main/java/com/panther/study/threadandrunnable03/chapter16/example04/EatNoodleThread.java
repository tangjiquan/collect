package com.panther.study.threadandrunnable03.chapter16.example04;

/**
 * @author: Kevin
 * @date: created in 下午10:54 2018-10-31
 * @version: V1.0
 */
public class EatNoodleThread extends Thread{

	private String name;
	private final TableWarePair tableWarePair;

	public EatNoodleThread(String name, TableWarePair tableWarePair){
		this.name = name;
		this.tableWarePair = tableWarePair;
	}

	@Override
	public void run() {
		while(true){
			this.eat();
		}
	}


	private void eat(){
		synchronized (tableWarePair){
			System.out.println(name + " take up " + tableWarePair.getLeftTool() + "(left) ");
			System.out.println(name + " take up " + tableWarePair.getRightTool() + "(right) ");
			System.out.println(name + "is eating now");
			System.out.println(name + " put down " + tableWarePair.getRightTool() + "(right)");
			System.out.println(name + " put down " + tableWarePair.getLeftTool() + "(left)");
		}
	}

	public static void main(String[] args){
		TableWare fork = new TableWare("fork");
		TableWare knife = new TableWare("knife");
		TableWarePair pair = new TableWarePair(fork, knife);
		new EatNoodleThread("a", pair).start();
		new EatNoodleThread("b", pair).start();
	}
}
