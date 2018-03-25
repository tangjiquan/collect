package com.panther.test.fourinone.chapter09;

import com.fourinone.Contractor;
import com.fourinone.WareHouse;

/**
 * @Author: Kevin
 * @Description:
 * @Date: Created in 下午4:23 18-3-25
 * @Version:
 */
public class StreamWorkerB extends Contractor {

    @Override
    public WareHouse giveTask(WareHouse inhouse) {
        System.out.println(inhouse);
        inhouse.put("msg", inhouse.getString("msg")+ "from streamworkerB");
        return inhouse;
    }

    public  static void main(String[] args){
        StreamWorkerB streamWorkerB = new StreamWorkerB();
        streamWorkerB.waitWorking("localhost", 2019, "StreamWorkerB");
    }
}
