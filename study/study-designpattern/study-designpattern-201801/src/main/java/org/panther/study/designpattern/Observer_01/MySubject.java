package org.panther.study.designpattern.Observer_01;

/**
 * @Author: Kevin
 * @Date: Created in 下午1:16 18-4-1
 * @Version:
 * @Description:
 */
public class MySubject extends AbstractSubject {

    public void operation() {
        System.out.println("mysubject operater");
        notifyObserver();
    }
}
