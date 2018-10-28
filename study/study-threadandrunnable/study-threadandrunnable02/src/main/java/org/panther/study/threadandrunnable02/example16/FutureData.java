package org.panther.study.threadandrunnable02.example16;

/**
 * @author: Kevin
 * @date: created in 下午7:04 2018-09-18
 * @version: V1.0
 */
public class FutureData implements  Data {
	private RealData realData;
	private boolean isReady = false;

	public synchronized  void setRealData(RealData data){
		// 如果已经加载， 则直接返回
		if(isReady){
			return ;
		}
		// 如果没有加载好，加载真实数据对象
		this.realData = data;
		isReady = true;
		// 进行通知
		notify();
	}
	@Override
	public  synchronized String getRequest() {
		// 如果没有加载好程序一直处于阻塞状态
	 	while(!isReady){
			try {
				wait();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		return this.realData.getRequest();
	}
}
