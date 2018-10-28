package com.panther.study.monitorandtuning.lesson01.chapter05;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author: Kevin
 * @date: created in 上午11:37 2018-10-05
 * @version: V1.0
 */

@RestController
@RequestMapping("/ch5")
public class Ch5controller {

	@RequestMapping("hello")
	public String hello(){
		String str = "0";
		for(int i=0; i<10; i++){
			str += i;
		}
		return str;
	}

}
