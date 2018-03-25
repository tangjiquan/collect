package com.panther.test.fourinone.chapter06;

import com.fourinone.BeanContext;
import com.fourinone.StartResult;

/**
 * @Author: Kevin
 * @Description:
 * @Date: Created in 上午10:35 18-3-25
 * @Version: 在一台机器上同时启动多个进程
 */
public class MainStart {

    public static void main(String[] args){
        System.out.println("start ParkServerDemo and waiting 4 secondes..");
        StartResult<Integer> parkserver = BeanContext.tryStart("java", "-cp", ".", "ParkServerDemp");

        try {
            Thread.sleep(4000L);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println("start HelloWorker and waiting 4 secondes..");
        StartResult<Integer> helloWorker1 = BeanContext.tryStart("java", "-cp", ".", "HelloWorker", "worker1", "localhost", "2008");
        StartResult<Integer> helloWorker2 = BeanContext.tryStart("java", "-cp", ".", "HelloWorker", "worker2", "localhost", "2009");

        try {
            Thread.sleep(4000L);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println("start HelloCtor and waiting 4 secondes..");
        StartResult<Integer> ctor = BeanContext.tryStart("java", "-cp", ".", "HelloCtor");


        while(true){
            if(ctor.getStatus()!=StartResult.NOTREADY){
                System.out.println("kill all");
                helloWorker1.kill();
                helloWorker2.kill();
                parkserver.kill();
                break;
            }
        }
    }
}
