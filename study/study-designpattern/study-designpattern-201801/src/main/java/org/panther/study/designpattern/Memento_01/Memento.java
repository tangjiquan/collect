package org.panther.study.designpattern.Memento_01;

/**
 * @Author: Kevin
 * @Date: Created in 下午10:37 18-4-1
 * @Version:
 * @Description:
 */
public class Memento {

    private String value;

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public Memento(String value){
        this.value = value;
    }
}
