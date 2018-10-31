package com.panther.study.threadandrunnable03.chapter16.example03;

/**
 * @author: Kevin
 * @date: created in 下午10:54 2018-10-31
 * @version: V1.0
 */
public class EatNoodleThread extends Thread{

	private String name;
	private final TableWare leftTool;
	private final TableWare rightTool;

	public EatNoodleThread(String name, TableWare leftTool, TableWare rightTool){
		this.name = name;
		this.leftTool = leftTool;
		this.rightTool = rightTool;
	}

	@Override
	public void run() {
		while(true){
			this.eat();
		}
	}


	private void eat(){
		synchronized (leftTool){
			System.out.println(name + " take up " + leftTool + "(left) ");
			synchronized (rightTool){
				System.out.println(name + " take up " + rightTool + "(right) ");
				System.out.println(name + "is eating now");
				System.out.println(name + " put down " + rightTool + "(right)");
			}
			System.out.println(name + " put down " + leftTool + "(left)");
		}
	}

	public static void main(String[] args){
		TableWare fork = new TableWare("fork");
		TableWare knife = new TableWare("knife");
		new EatNoodleThread("a", fork, knife).start();
		new EatNoodleThread("b", fork, knife).start();


	}
}
