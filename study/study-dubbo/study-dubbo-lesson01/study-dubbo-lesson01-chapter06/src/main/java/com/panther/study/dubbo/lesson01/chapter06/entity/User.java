package com.panther.study.dubbo.lesson01.chapter06.entity;

import com.fasterxml.jackson.annotation.JsonProperty;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Null;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * @author: Kevin
 * @date: created in 下午9:26 2018-10-03
 * @version: V1.0
 */

@XmlRootElement
public class User {

	@NotNull
	private String id;

	@JsonProperty("name")
	@Null
	@Size(min=6, max=50)
	private String name;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
}
