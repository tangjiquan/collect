package com.panther.test.fourinone.chapter05;

import com.fourinone.MigrantWorker;
import com.fourinone.WareHouse;

/**
 * @Author: Kevin
 * @Description:
 * @Date: Created in 上午10:09 18-3-25
 * @Version:
 */
public class FaultWorker extends MigrantWorker{

    @Override
    protected WareHouse doTask(WareHouse inhouse) {
        System.out.println(inhouse.getString("word"));
        try {
            Thread.sleep(8000L);
        }catch (Exception e){
            e.printStackTrace();
        }

        String str = null;
        System.out.println(str.length());
        WareHouse wh = new WareHouse("word", "hello");
        return wh;
    }

    public static void main(String[] args){
        FaultWorker faultWorker = new FaultWorker();
        faultWorker.waitWorking("localhost", 2008, "simpleworker");
    }
}
