package org.panther.study.threadandrunnable02.example17;

/**
 * @author: Kevin
 * @date: created in 下午8:31 2018-09-18
 * @version: V1.0
 */
public class Main {

	public static void main(String[] args){
		Master master = new Master(new Worker(), 1000);

		for(int i=1;i<=10000; i++){
			Task t = new Task();
			t.setId(i);
			t.setPrice(10);
			master.submit(t);
		}

		master.exec();
		long startTime = System.currentTimeMillis();

		while(true){
			if(master.isComplate()){
				long end = System.currentTimeMillis() - startTime;
				int priceTotal = master.getResult();
				System.out.println("总耗时：" + end);
				System.out.println("总数:" + priceTotal);
				break;
			}
		}
	}
}
