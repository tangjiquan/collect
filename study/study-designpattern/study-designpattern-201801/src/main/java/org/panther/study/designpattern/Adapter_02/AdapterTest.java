package org.panther.study.designpattern.Adapter_02;

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
     * 对象的适配器模式
     * Wrapper持有对象source达到扩展功能的效果
     * @param args
     */
    public static void main(String[] args){

        Source source = new Source();
        Targetable targetable = new Wrapper(source);
        targetable.method1();
        targetable.method2();
    }
}
