package org.panther.study.designpattern.State_01;

/**
 * @Author: Kevin
 * @Date: Created in 下午10:19 18-4-1
 * @Version:
 * @Description:
 */
public class State {

    private String value;

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public void method1(){
        System.out.println("exec method1");
    }

    public void method2(){
        System.out.println("exec method2");
    }

}
