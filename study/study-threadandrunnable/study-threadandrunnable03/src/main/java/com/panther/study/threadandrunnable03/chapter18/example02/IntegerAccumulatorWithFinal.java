package com.panther.study.threadandrunnable03.chapter18.example02;

/**
 * IntegerAccumulatorClassSycn使用了同步代码块实现了线程安全
 * 下面的案例使用不可变对象实现了线程安全， 每一次add时候都返回一个变量
 *
 * @author: Kevin
 * @date: created in 下午8:38 2018-11-05
 * @version: V1.0
 */
// 不可变对象不能被继承
public final class IntegerAccumulatorWithFinal {

	private final int init;


	public IntegerAccumulatorWithFinal(int init){
		this.init = init;
	}

	public IntegerAccumulatorWithFinal(IntegerAccumulatorWithFinal integerAccumulatorWithFinal, int init){
		this.init = integerAccumulatorWithFinal.getValue() + init;
	}

	public IntegerAccumulatorWithFinal add(int i){
		return new IntegerAccumulatorWithFinal(this, i);
	}

	public int getValue(){
		return init;
	}

	public static void main(String[] args){
		final IntegerAccumulatorWithFinal integerAccumulator = new IntegerAccumulatorWithFinal(0);

		for(int i=0; i<3; i++){
			new Thread(){
				@Override
				public void run() {
					int inc = 0;
					while(true){
						int oldValue;
						int result;
						oldValue = integerAccumulator.getValue();
						result = integerAccumulator.add(inc).getValue();
						//System.out.println(oldValue + "+" + inc + " = " + result);

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
