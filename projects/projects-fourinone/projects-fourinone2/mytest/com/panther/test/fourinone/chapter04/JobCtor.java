package com.panther.test.fourinone.chapter04;

import com.fourinone.Contractor;
import com.fourinone.WareHouse;
import com.fourinone.WorkerLocal;

/**
 * @Author: Kevin
 * @Description:
 * @Date: Created in 上午9:54 18-3-25
 * @Version: 分布式就算自动部署， 如果不需要自动部署， 将工人， 工头分别部署在相同机器上运行就可以
 *
 */
public class JobCtor extends Contractor{

    @Override
    public WareHouse giveTask(WareHouse inhouse) {
        WorkerLocal[] wks = getWaitingWorkers("simpleworker");
        System.out.println("worker length" + wks.length);
        wks[0].setWorker(new JobWorker());
        WareHouse wh = new WareHouse("word", "hello");
        WareHouse result = wks[0].doTask(wh);
        System.out.println("result : " + result);

        while(true){
            if(result.isReady()){
                System.out.println("resuntl : " + result);
                break;
            }
        }
        return null;
    }

    public static void main(String[] args){
        JobCtor jobCtor = new JobCtor();
        jobCtor.giveTask(null);
        jobCtor.exit();
    }
}
