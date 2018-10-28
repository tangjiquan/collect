package org.panther.study.threadandrunnable02.example16;

/**
 * @author: Kevin
 * @date: created in 下午6:58 2018-09-18
 * @version: V1.0
 */
public class MainTest {

	public static void main(String[] args){
		FutureClient fc = new FutureClient();
		Data data = fc.request("请求参数");
		System.out.println("请求发话成功");
		System.out.println("其他事情");
		String result = data.getRequest();
		System.out.println(result);
	}
}
