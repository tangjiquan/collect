package org.panther.study.threadandrunnable02.example04;

/**
 * getValue()方法上加synchronized， 那么在调用getvalue的时候需要等到锁， 这样setvalue肯定先执行， 然后再执行getvalue
 * @author: Kevin
 * @date: created in 下午2:36 2018-08-26
 * @version: V1.0
 */
public class DirtyRead02 {

	private String username ="11";
	private String password ="22";

	public synchronized void setValue(String username, String password){
		this.username = username;
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		this.password = password;

		System.out.println("setvalue的值： username=" + username +" password= " + password);
	}

	public  synchronized void getValue(){
		System.out.println("getvalue的值： username=" + username +" password= " + password);
	}


	public static void main(String[] args) throws InterruptedException {
		final DirtyRead02 dr = new DirtyRead02();
		Thread t1 = new Thread(new Runnable() {
			@Override
			public void run() {
				dr.setValue("aa", "bb");
			}
		});

		t1.start();
		Thread.sleep(1000);
		dr.getValue();
	}
}
