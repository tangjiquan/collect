package com.panther.mybatis.test.example01;

/**
 * @author: Kevin
 * @date: created in 下午9:36 2018-10-28
 * @version: V1.0
 */
public class User {

	private String name;
	private String id;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	@Override
	public String toString() {
		return "User{" +
				"name='" + name + '\'' +
				", id='" + id + '\'' +
				'}';
	}
}
