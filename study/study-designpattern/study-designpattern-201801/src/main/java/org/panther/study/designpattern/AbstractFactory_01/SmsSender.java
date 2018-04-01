package org.panther.study.designpattern.AbstractFactory_01;

/**
 * @Author: Kevin
 * @Description:
 * @Date: Created in 下午10:38 18-2-6
 * @Version:
 */
public class SmsSender implements Sender {
    @Override
    public void send() {
        System.out.println("smssend....");
    }
}
