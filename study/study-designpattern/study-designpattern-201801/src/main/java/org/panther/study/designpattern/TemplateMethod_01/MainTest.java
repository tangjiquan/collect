package org.panther.study.designpattern.TemplateMethod_01;

/**
 * @Author: Kevin
 * @Date: Created in 下午12:49 18-4-1
 * @Version:
 * @Description:
 * 一个抽象类中，有一个主方法，再定义1...n个方法，可以是抽象的，也可以是实际的方法，定义一个类，继承该抽象类，重写抽象方法，通过调用抽象类，实现对子类的调用，
 * 与策略模式的区别是：策略模式调用的是调用子类自身具体实现的方法， 模板方法模式是调用父类暴露的方法， 父类的方法再调用自己的方法
 */
public class MainTest {

    public static void main(String[] args){
        String exp = "8+8";
        AbstractCalculator cal = new Plus();
        int result = cal.calculate(exp, "\\+");
        System.out.println(result);
    }
}
