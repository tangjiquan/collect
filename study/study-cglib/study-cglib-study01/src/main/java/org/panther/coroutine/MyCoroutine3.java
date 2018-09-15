package org.panther.coroutine;

import com.offbynull.coroutines.user.Continuation;
import com.offbynull.coroutines.user.Coroutine;

/**
 * @author: Kevin
 * @date: created in 下午4:57 2018-08-13
 * @version: V1.0
 */
public class MyCoroutine3 implements Coroutine {

	@Override
	public void run(Continuation c) {
		System.out.println("[TASK 3] In task 3, doing something...");
		System.out.println("[TASK 3] Finish task 3 !");
	}

}
