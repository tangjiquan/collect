package com.panther.test.fourinone.chapter09;

import com.fourinone.Contractor;
import com.fourinone.WareHouse;
import com.fourinone.WorkerLocal;

/**
 * @Author: Kevin
 * @Description:
 * @Date: Created in 下午4:20 18-3-25
 * @Version:
 */
public class StreamCtorB extends Contractor{

    @Override
    public WareHouse giveTask(WareHouse inhouse) {
        WorkerLocal[] wkl = getWaitingWorkers("StreamWorkerB");
        System.out.println("StreamWorkerB length ： " + wkl.length);
        WareHouse[] whs = doTaskBatch(wkl, inhouse);
        WareHouse result = new WareHouse();
        result.put("B1", whs[0]);
        result.put("B2", whs[1]);
        return  result;
    }
}
