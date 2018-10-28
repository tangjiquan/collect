package org.panther.study.threadandrunnable02.example16;

/**
 * @author: Kevin
 * @date: created in 下午7:04 2018-09-18
 * @version: V1.0
 */
public class RealData implements Data {
	private String result;

	public  RealData(String request) {
		System.out.println("依据"+ request + "进行查询，这是一个很耗时操作");
		try {
			Thread.sleep(5000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

		System.out.println("获取结果");
		result = "查询结果";
	}

	@Override
	public String getRequest(){
		return result;
	}
}
