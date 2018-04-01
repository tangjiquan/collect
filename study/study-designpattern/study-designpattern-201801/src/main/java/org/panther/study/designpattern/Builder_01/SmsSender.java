package org.panther.study.designpattern.Builder_01;

/**
 * @Author: Kevin
 * @Description:
 * @Date: Created in 下午10:40 18-2-10
 * @Version:
 */
public class SmsSender implements Sender {
    @Override
    public void send() {
        System.out.println("smssend....");
    }
}
