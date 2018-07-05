package org.jq.controller;

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


	@RequestMapping("/index")
	public String index(){
		return "index";
	}

	@RequestMapping("/out")
	@ResponseBody
	public Date out(Date data){
		return data;
	}
}
