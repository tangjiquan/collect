package org.panther.study.springboot.example01;

import java.io.IOException;

/**
 * 后台启动一条线程不断刷新重新加载实现了热加载的类
 *
 * @author: Kevin
 * @date: created in 上午11:19 2018-07-08
 * @version: V1.0
 */
public class MsgHandler implements Runnable{
	@Override
	public void run() {
		while (true){
			try {
				BaseManager manager = ManagerFactory.getManager(ManagerFactory.MY_MANAGER);
				manager.logic();
				try {
					Thread.sleep(1000);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			} catch (IOException e) {
				e.printStackTrace();
			}

		}
	}
}
