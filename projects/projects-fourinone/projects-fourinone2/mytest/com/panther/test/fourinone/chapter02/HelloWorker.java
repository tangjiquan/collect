package com.panther.test.fourinone.chapter02;

import com.fourinone.MigrantWorker;
import com.fourinone.WareHouse;
import com.fourinone.Workman;

/**
 * @Author: Kevin
 * @Description:
 * @Date: Created in 下午11:18 18-3-24
 * @Version:
 */
public class HelloWorker extends MigrantWorker{

    private String name;
    public HelloWorker(String name){
        this.name = name;
    }

    public WareHouse doTask(WareHouse inhouse){
        System.out.println(inhouse.getString("word"));
        WareHouse wh = new WareHouse("word", "hello , i am  " + name);
        Workman[] wms = getWorkerElse("helloworker");
        for(Workman wk : wms){
            wk.receive(wh);
        }
        return wh;
    }

    @Override
    protected boolean receive(WareHouse inhouse) {
        System.out.println(inhouse.getString("word"));
        return true;
    }

    public static void main(String[] args){
        HelloWorker mw = new HelloWorker("bbb");
        mw.waitWorking("localhost", 2009, "helloworker");
    }



}
