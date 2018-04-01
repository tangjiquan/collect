package org.panther.study.designpattern.FactoryMethod_03;

/**
 * @Author: Kevin
 * @Description: 工厂方法模式--静态厂方法模式
 *  将多个工厂方法模式中的方法置为静态，不需创建实例，直接调用即可
 * @Date: Created in 下午10:43 18-2-6
 * @Version:
 */
public class MainTest {

    public static  void main(String[] args){
        Sender sender = SenderFactory.produceMail();
        sender.send();
    }
}
