package com.panther.test.fourinone.chapter09;

import com.fourinone.MigrantWorker;
import com.fourinone.WareHouse;

/**
 * @Author: Kevin
 * @Description:
 * @Date: Created in 下午4:18 18-3-25
 * @Version:
 */
public class StreamWorkerA extends MigrantWorker {

    @Override
    protected WareHouse doTask(WareHouse inhouse) {
        System.out.println(inhouse);
        StreamCtorB streamCtorB = new StreamCtorB();
        WareHouse msg = new WareHouse();
        msg.put("msg", inhouse.getString("msg")+ ", from StreamWorkerA");
        WareHouse result = streamCtorB.giveTask(msg);

        return result;
    }

    public static void main(String[] args){
        StreamWorkerA streamWorkerA = new StreamWorkerA();
        streamWorkerA.waitWorking("localhsot", 2008, "StreamWorkerA");
    }
}
