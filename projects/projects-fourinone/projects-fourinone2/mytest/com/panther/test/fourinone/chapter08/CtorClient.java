package com.panther.test.fourinone.chapter08;

import com.fourinone.Contractor;
import com.fourinone.WareHouse;
import com.fourinone.WorkerLocal;

/**
 * @Author: Kevin
 * @Description:
 * @Date: Created in 下午3:55 18-3-25
 * @Version:
 */
public class CtorClient extends Contractor {

    public String sayHello(String name){
        WareHouse wh = giveTask(new WareHouse("inputString", name));
        return wh.getString("result");
    }
    @Override
    public WareHouse giveTask(WareHouse inhouse) {
        WorkerLocal[] wkl = getWaitingWorkers("workerService");
        WareHouse[] results = doTaskBatch(wkl, inhouse);
        return results[0];
    }

    public static void main(String[] args){
        CtorClient ctorClient = new CtorClient();
        String serviceResult = ctorClient.sayHello("zhangsan");

        System.out.println(serviceResult);
        ctorClient.exit();
    }
}
