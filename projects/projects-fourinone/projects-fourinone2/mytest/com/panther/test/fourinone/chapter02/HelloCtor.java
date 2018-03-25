package com.panther.test.fourinone.chapter02;

import com.fourinone.Contractor;
import com.fourinone.WareHouse;
import com.fourinone.WorkerLocal;

/**
 * @Author: Kevin
 * @Description:
 * @Date: Created in 下午11:28 18-3-24
 * @Version:
 */
public class HelloCtor extends Contractor {

    @Override
    public WareHouse giveTask(WareHouse inhouse) {
        WorkerLocal[] wks = getWaitingWorkers("helloworker");
        System.out.println("worker length ： " + wks.length);
        WareHouse wh = new WareHouse("word", "hello , i am your contractor");
        WareHouse[] whs = doTaskBatch(wks, wh);
        for(WareHouse result : whs){
            System.out.println(result);
        }

        return  null;
    }


    public static void main(String[] args){
        HelloCtor ctor = new HelloCtor();
        ctor.giveTask(null);
        //ctor.exit();
    }
}
