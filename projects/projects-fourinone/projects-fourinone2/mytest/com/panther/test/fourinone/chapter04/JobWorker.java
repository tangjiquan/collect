package com.panther.test.fourinone.chapter04;

import com.fourinone.MigrantWorker;
import com.fourinone.WareHouse;

/**
 * @Author: Kevin
 * @Description:
 * @Date: Created in 上午9:48 18-3-25
 * @Version:
 */
public class JobWorker extends MigrantWorker{

    @Override
    protected WareHouse doTask(WareHouse inhouse) {
        String word = inhouse.getString("word");
        System.out.println(word + " from Contractor");
        // 停止2秒中， 模拟任务执行
        try {
            Thread.sleep(2000L);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("done...");
        return new WareHouse("word", "work done...........");

    }
}
