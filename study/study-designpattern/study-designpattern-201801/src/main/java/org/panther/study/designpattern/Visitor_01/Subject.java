package org.panther.study.designpattern.Visitor_01;

/**
 * @Author: Kevin
 * @Date: Created in 下午6:51 18-4-1
 * @Version:
 * @Description:
 */
public interface Subject {

    public void accept(Visitor visitor);
    public String getSubject();
}
