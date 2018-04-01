package org.panther.study.designpattern.AbstractFactory_01;

/**
 * @Author: Kevin
 * @Description: 抽象工厂模式
 * @Date: Created in 下午11:02 18-2-6
 * @Version:
 */
public class MainTest {

    public static void main(String[] args){
        Provider provider = new SmsProvider();
        Sender sender = provider.produce();
        sender.send();
    }

}
