package com.panther.test.fourinone.chapter08;

import com.fourinone.BeanContext;
import com.fourinone.StartResult;

/**
 * @Author: Kevin
 * @Description:
 * @Date: Created in 下午4:01 18-3-25
 * @Version: 通过tryStart启动ctor的服务
 */
public class ClientMain {

    public static void main(String[] args){
        StartResult<Integer> ctor1 = BeanContext.tryStart("java", "-cp", ".", "CtorClient", "client1");

        StartResult<Integer> ctor2 = BeanContext.tryStart("java", "-cp", ".", "CtorClient", "client2");
    }
}
