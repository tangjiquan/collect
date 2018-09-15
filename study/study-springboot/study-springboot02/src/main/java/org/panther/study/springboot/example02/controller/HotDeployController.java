package org.panther.study.springboot.example02.controller;

import org.apache.catalina.servlet4preview.http.HttpServletRequest;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author: Kevin
 * @date: created in 下午9:11 2018-07-08
 * @version: V1.0
 */
@Controller
public class HotDeployController {

	@RequestMapping("/say")
	public String say(HttpServletRequest request){
		request.setAttribute("say", "d saddy");
		return "sayshow";
	}
}
