package com.panther.study.dubbo.lesson01.chapter06.test.http;

import com.panther.study.dubbo.lesson01.chapter06.entity.User;
import com.panther.study.dubbo.lesson01.chapter06.util.FastJsonConvert;
import com.panther.study.dubbo.lesson01.chapter06.util.HttpProxy;


public class HttpTest1 {

	public static void main(String[] args) throws Exception{
    	User user = new User();
    	user.setId("1001");
    	user.setName("李四");
		String responseStr1 = HttpProxy.postJson("http://localhost:8888/provider/userService/postUser",
				FastJsonConvert.convertObjectToJSON(user));
		System.out.println(responseStr1);
		
		
	}
}
