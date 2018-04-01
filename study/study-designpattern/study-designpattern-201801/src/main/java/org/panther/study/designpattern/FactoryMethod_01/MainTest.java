package org.panther.study.designpattern.FactoryMethod_01;

/**
 * @Author: Kevin
 * @Description: 工厂方法模式--普通工厂模式
 * @Date: Created in 下午10:43 18-2-6
 * @Version:
 */
public class MainTest {

    public static  void main(String[] args){
        SenderFactory factory = new SenderFactory();
        Sender sender = factory.produce("mail");
        sender.send();
    }
}
