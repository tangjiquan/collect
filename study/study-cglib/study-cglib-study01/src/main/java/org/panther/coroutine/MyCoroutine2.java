package org.panther.coroutine;

import com.offbynull.coroutines.user.Continuation;
import com.offbynull.coroutines.user.Coroutine;

/**
 * @author: Kevin
 * @date: created in 下午4:57 2018-08-13
 * @version: V1.0
 */
public class MyCoroutine1  implements Coroutine {

	@Override
	public void run(Continuation c) {
		System.out.println("[TASK 1] In task 1, doing something...");
		System.out.println("[TASK 1] Switch to other task.");
		c.suspend();
		System.out.println("[TASK 1] In task 1, doing something...");
		System.out.println("[TASK 1] Switch to other task.");
		c.suspend();
		System.out.println("[TASK 1] In task 1, doing something...");
		System.out.println("[TASK 1] Finish task 1 !");
	}


}
