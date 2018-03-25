package com.panther.test.fourinone.chapter07;

import com.fourinone.MigrantWorker;
import com.fourinone.WareHouse;

import java.util.Random;

/**
 * @Author: Kevin
 * @Description:
 * @Date: Created in 下午3:12 18-3-25
 * @Version:
 */
public class CancelWorker extends MigrantWorker{

    @Override
    protected WareHouse doTask(WareHouse inhouse) {
        int n = 0;
        Random random = new Random();
       /* while(!isInterrupted()){
            n = random.nextInt(10000);
            System.out.println(n);
            if(n == 888){
                break;
            }
        }*/

        return new WareHouse("result", n);
    }

    public static void main(String[] args){
        CancelWorker cancelWorker = new CancelWorker();
        cancelWorker.waitWorking("localhost", 2009, "cancelworker");
    }

}
