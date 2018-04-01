package org.panther.study.designpattern.Visitor_01;

/**
 * @Author: Kevin
 * @Date: Created in 下午6:54 18-4-1
 * @Version:
 * @Description:
 */
public class MainTest {

    public static void main(String[] args){
        Visitor visitor = new MyVisitor();
        Subject subject = new MySubject();
        subject.accept(visitor);
    }
}
