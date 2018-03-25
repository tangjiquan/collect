package com.panther.test.fourinone.chapter01;

import com.fourinone.MigrantWorker;
import com.fourinone.WareHouse;

/**
 * @Author: Kevin
 * @Description:
 * @Date: Created in 下午10:26 18-3-24
 * @Version:
 */
public class SimpleWorker extends MigrantWorker{

    @Override
    protected WareHouse doTask(WareHouse inhouse) {
        String word = inhouse.getString("word");
        System.out.println(word + " from Contractor.");
        return new WareHouse("word", word + " world");
    }

    public static  void main(String[] args){
        SimpleWorker worker = new SimpleWorker();
        worker.waitWorking("simpleworder");
    }
}
