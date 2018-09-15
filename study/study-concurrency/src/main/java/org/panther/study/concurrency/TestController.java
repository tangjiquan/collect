package org.panther.study.concurrency;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @author: Kevin
 * @date: created in 下午10:36 2018-07-07
 * @version: V1.0
 */

@Controller
public class TestController {

	@RequestMapping("/test1mapping")
	@ResponseBody
	public String test(){
		return "test1";
	}
}
