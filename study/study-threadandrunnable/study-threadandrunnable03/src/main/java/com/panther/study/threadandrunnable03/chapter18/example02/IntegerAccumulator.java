package com.panther.study.threadandrunnable03.chapter18.example02;

/**
 * IntegerAccumulator出现了线程安全问题， 共享资源被多个线程操作，
 * 偶尔会出现错误情况
 *
 * @author: Kevin
 * @date: created in 下午8:38 2018-11-05
 * @version: V1.0
 */
public class IntegerAccumulator {

	private int init;

	public IntegerAccumulator(int init){
		this.init = init;
	}

	public int add(int i){
		this.init += i;
		return this.init;
	}

	public int getValue(){
		return init;
	}

	public static void main(String[] args){
		final IntegerAccumulator integerAccumulator = new IntegerAccumulator(0);

		for(int i=0; i<3; i++){
			new Thread(){
				@Override
				public void run() {
					int inc = 0;
					while(true){
						int oldValue = integerAccumulator.getValue();

						int result = integerAccumulator.add(inc);

						System.out.println(oldValue + "+" + inc + " = " + result);

						// 验证，如果不合理则输出错误信息
						if(inc + oldValue != result){
							System.err.println("ERROR:"+ oldValue + "+" + inc + " = " + result);
						}

						inc++;
						/*try {
							TimeUnit.SECONDS.sleep(1);
						} catch (InterruptedException e) {
							e.printStackTrace();
						}*/
					}
				}
			}.start();
		}
	}
}
