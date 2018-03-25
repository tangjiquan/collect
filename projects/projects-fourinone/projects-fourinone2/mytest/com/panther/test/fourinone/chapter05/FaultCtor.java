package com.panther.test.fourinone.chapter05;

import com.fourinone.Contractor;
import com.fourinone.WareHouse;
import com.fourinone.WorkerLocal;

/**
 * @Author: Kevin
 * @Description:
 * @Date: Created in 上午10:12 18-3-25
 * @Version: 计算过程中故障和容灾处理
 */
public class FaultCtor extends Contractor{

    @Override
    public WareHouse giveTask(WareHouse inhouse) {
        WorkerLocal[] wks = getWaitingWorkers("simpleworker");
        System.out.println("worker length :" + wks.length);

        WareHouse wh = new WareHouse("word", "hello");
        WareHouse result = wks[0].doTask(wh);

        while(true){
            if(result.getStatus() == WareHouse.READY){
                System.out.println("result :" + result);
                break;
            }
            if(result.getStatus() == WareHouse.EXCEPTION){
                System.out.println("result exception");
                break;
            }
        }
        return null;
    }

    public static void main(String[] args){
        FaultCtor faultCtor = new FaultCtor();
        faultCtor.giveTask(null);
        faultCtor.exit();
    }
}
