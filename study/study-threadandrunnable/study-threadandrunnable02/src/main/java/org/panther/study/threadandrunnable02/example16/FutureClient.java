package org.panther.study.threadandrunnable02.example16;

/**
 * @author: Kevin
 * @date: created in 下午6:59 2018-09-18
 * @version: V1.0
 */
public class FutureClient {

	public Data request(final String request){
		// 代理对象（Data接口的实现类， 先返回给请的客户端，告诉他请求已经结束到， 可以做其他的事情
		final FutureData futureData = new FutureData();
		// 启动一个线程加载数据， 传递给这个代理对象
		new Thread(new Runnable() {
			@Override
			public void run() {
				// 这个新的线程去加载真实对象， 然后传递给代理对象
				RealData realData = new RealData(request);
				futureData.setRealData(realData);
			}
		}).start();

		return futureData;
	}
}
