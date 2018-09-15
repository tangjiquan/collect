package org.panther.study.threadandrunnable02.example13;

import java.util.Iterator;
import java.util.Vector;

/**
 *
 * @author: Kevin
 * @date: created in 上午9:44 2018-09-10
 * @version: V1.0
 */
public class Tickets01 {
	public static void main(String[] args){
		// 初始化火车票并添加火车票，线程同步使用Vector代替ArrayList，HashTable代替HashMap
		final Vector<String> tickes = new Vector<String>();
		for(int i=0; i<1000; i++){
			tickes.add("火车票" + i);
		}

		for(Iterator iterator=tickes.iterator(); iterator.hasNext();){
			String string = (String)iterator.next();
			tickes.remove(20);
		}

	}

}
