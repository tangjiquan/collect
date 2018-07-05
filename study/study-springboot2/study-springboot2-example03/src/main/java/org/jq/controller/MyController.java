package org.jq.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Date;

/**
 * @Author: Kevin
 * @Date: Created in 下午3:59 18-5-1
 * @Version:
 * @Description:
 */

@Controller
public class MyController {

	@Value("${app.name}")
	private String name;

	@RequestMapping("/index")
	public String index(){
		return "index";
	}

	//@Resource
	//private AppConfig appConfig;

	@RequestMapping("/out")
	@ResponseBody
	public Date out(Date data){
		return data ;
	}

	@RequestMapping("/name")
	@ResponseBody
	public String out(){
		return name;
	}
}
