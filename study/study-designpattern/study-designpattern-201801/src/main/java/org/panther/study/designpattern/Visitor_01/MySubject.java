package org.panther.study.designpattern.Visitor_01;

/**
 * @Author: Kevin
 * @Date: Created in 下午6:53 18-4-1
 * @Version:
 * @Description:
 */
public class MySubject implements Subject {

    public void accept(Visitor visitor) {
        visitor.visit(this);
    }

    public String getSubject() {
        return "hell";
    }
}
