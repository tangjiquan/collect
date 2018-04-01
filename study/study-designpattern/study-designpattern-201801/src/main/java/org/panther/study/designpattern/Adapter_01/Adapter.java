package org.panther.study.designpattern.Adapter_01;

/**
 * @Author: Kevin
 * @Description:
 * @Date: Created in 下午11:55 18-3-28
 * @Version:
 */
public class Adapter extends Source implements Targetable{


    public void method2() {
        System.out.println("this is method2");
    }
}
