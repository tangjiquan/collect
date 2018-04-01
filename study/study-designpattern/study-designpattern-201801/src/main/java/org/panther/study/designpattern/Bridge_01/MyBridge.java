package org.panther.study.designpattern.Bridge_01;

/**
 * @Author: Kevin
 * @Description:
 * @Date: Created in 上午1:26 18-3-29
 * @Version:
 */
public class MyBridge extends Bridge{

    public void method(){
        getSource().method();
    }
}
