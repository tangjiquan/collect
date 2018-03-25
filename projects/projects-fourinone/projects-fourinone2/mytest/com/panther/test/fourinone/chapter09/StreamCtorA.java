package com.panther.test.fourinone.chapter09;

import com.fourinone.Contractor;
import com.fourinone.WareHouse;
import com.fourinone.WorkerLocal;

/**
 * @Author: Kevin
 * @Description:
 * @Date: Created in 下午4:12 18-3-25
 * @Version:
 */
public class StreamCtorA extends Contractor {

    @Override
    public WareHouse giveTask(WareHouse inhouse) {
        WorkerLocal[] wkl = getWaitingWorkers("StreamWorkerA");
        System.out.println("worker length : " + wkl.length);
        WareHouse result = wkl[0].doTask(inhouse);
        while(true){
            if(result.getStatus() != WareHouse.NOTREADY){
                break;
            }
        }
        return result;
    }

    public static void main(String[] args){
        StreamCtorA streamCtorA = new StreamCtorA();
        for(int i=0; i<10; i++){
            WareHouse wh = new WareHouse();
            wh.put("msg", "hello" + i);
            WareHouse result = streamCtorA.giveTask(wh);
            System.out.println(result);
        }
    }
}
