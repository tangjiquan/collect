package com.panther.test.fourinone.chapter01;

import com.fourinone.Contractor;
import com.fourinone.WareHouse;
import com.fourinone.WorkerLocal;

/**
 * @Author: Kevin
 * @Description:
 * @Date: Created in 下午10:29 18-3-24
 * @Version:
 */
public class SimpleCtor extends Contractor{

    @Override
    public WareHouse giveTask(WareHouse inhouse) {
        WorkerLocal[] wks = getWaitingWorkers("simpleworder");
        System.out.println("worker length: " + wks.length);

        WareHouse wh  = new WareHouse("word", "hello");
        WareHouse result = wks[0].doTask(wh);

        while(true){
            if(result.getStatus() == WareHouse.READY){
                System.out.println("result : " + result);
                break;
            }
        }
        return null;
    }

    public static void main(String[] args){
        SimpleCtor  a = new SimpleCtor();
        a.giveTask(null);
    }
}
