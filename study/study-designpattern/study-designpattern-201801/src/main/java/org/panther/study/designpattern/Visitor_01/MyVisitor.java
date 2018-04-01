package org.panther.study.designpattern.Visitor_01;

/**
 * @Author: Kevin
 * @Date: Created in 下午6:52 18-4-1
 * @Version:
 * @Description:
 */
public class MyVisitor implements Visitor{

    public void visit(Subject subject) {
        System.out.println("visitor the subject :" + subject.getSubject());
    }
}
