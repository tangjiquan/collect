package org.panther.study.designpattern.Adapter_01;

/**
 * @Author: Kevin
 * @Description: 适配器模式将某个接口转换成客户端期望的另一个接口表示。目的是消除接口间不匹配造成的兼容性问题
 *              主要分为三类：类的适配器模式， 对象的适配器模式， 接口的适配器模式
 * @Date: Created in 下午11:57 18-3-28
 * @Version:
 *
 */
public class AdapterTest {

    /**
     * 类的适配器模式
     * 有一个Source类，拥有一个方法，待适配，目标接口时Targetable，通过Adapter类，将Source的功能扩展到Targetable里
     * @param args
     */
    public static void main(String[] args){
        Targetable targetable = new Adapter();
        targetable.method1();
        targetable.method2();
    }
}
