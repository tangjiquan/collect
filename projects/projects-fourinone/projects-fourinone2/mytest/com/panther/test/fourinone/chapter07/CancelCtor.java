package com.panther.test.fourinone.chapter07;

import com.fourinone.Contractor;
import com.fourinone.WareHouse;
import com.fourinone.WorkerLocal;

/**
 * @Author: Kevin
 * @Description:
 * @Date: Created in 下午3:22 18-3-25
 * @Version:
 */
public class CancelCtor extends Contractor {

    @Override
    public WareHouse giveTask(WareHouse inhouse) {
        WorkerLocal[] wkl = getWaitingWorkers("cancelworker");
        System.out.println("worker length: " + wkl.length);

        WareHouse[] whs = new WareHouse[wkl.length];
        for(int i=0;i<wkl.length;i++){
            whs[i] = wkl[i].doTask(new WareHouse());
        }

        return null;
    }
}
