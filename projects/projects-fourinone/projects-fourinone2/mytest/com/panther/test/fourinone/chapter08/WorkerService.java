package com.panther.test.fourinone.chapter08;

import com.fourinone.MigrantWorker;
import com.fourinone.WareHouse;

/**
 * @Author: Kevin
 * @Description:
 * @Date: Created in 下午3:51 18-3-25
 * @Version:
 */
public class WorkerService extends MigrantWorker {

    @Override
    protected WareHouse doTask(WareHouse inhouse) {
        String inputString = inhouse.getString("InputString");
        return new WareHouse("result", inputString + ",  hello");
    }

    public static void main(String[] args){
        WorkerService ws = new WorkerService();

        // 启动服务， <SERVICE>true</SERVICE>
        ws.waitWorking("localhost", 2008, "WorkerService");
    }
}
