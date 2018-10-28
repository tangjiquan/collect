package com.panther.study.monitorandtuning.lesson01.chapter02;

/**
 * @author: Kevin
 * @date: created in 上午12:15 2018-10-01
 * @version: V1.0
 */
public class User {

	private int id;
	private String name;

	public User(int id, String name) {
		this.id = id;
		this.name = name;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
}
