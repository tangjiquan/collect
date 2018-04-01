package org.panther.study.designpattern.Adapter_02;

/**
 * @Author: Kevin
 * @Description:
 * @Date: Created in 下午11:55 18-3-28
 * @Version:
 */
public class Wrapper  implements Targetable {

    private Source source;

    public Wrapper(Source source){
        this.source = source;
    }

    public void method1() {
        this.source.method1();
    }

    public void method2() {
        System.out.println("this is method2");
    }
}
