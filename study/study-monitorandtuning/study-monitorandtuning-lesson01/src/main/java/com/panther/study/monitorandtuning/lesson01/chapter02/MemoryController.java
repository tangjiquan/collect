package com.panther.study.monitorandtuning.lesson01.chapter02;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/**
 * @author: Kevin
 * @date: created in 上午12:10 2018-10-01
 * @version: V1.0
 */

@RestController
public class MemoryController {

	private List<User> userList = new ArrayList<User>();

	/**
	 * 设置堆栈内存大小
	 * -Xmx 32M -Xms 32M
	 *
	 * @return
	 */
	@GetMapping("heap")
	public String heap(){
		int i = 0;
		while(true){
			userList.add(new User(i++, UUID.randomUUID().toString()));
		}
	}
}
