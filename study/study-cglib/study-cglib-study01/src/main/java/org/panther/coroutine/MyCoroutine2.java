package org.panther.coroutine;

import com.offbynull.coroutines.user.Continuation;
import com.offbynull.coroutines.user.Coroutine;

/**
 * @author: Kevin
 * @date: created in 下午4:57 2018-08-13
 * @version: V1.0
 */
public class MyCoroutine2 implements Coroutine {

	@Override
	public void run(Continuation c) {
		System.out.println("[TASK 2] In task 2, doing something...");
		System.out.println("[TASK 2] Switch to other task.");
		//c.suspend();
		System.out.println("[TASK 2] In task 2, doing something...");
		System.out.println("[TASK 2] Finish task 2 !");

	}


}
