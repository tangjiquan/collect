package org.panther.study.designpattern.Observer_01;

/**
 * @Author: Kevin
 * @Date: Created in 下午1:12 18-4-1
 * @Version:
 * @Description:
 */
public interface Subject {

    public void add(Observer observer);

    public void del(Observer observer);

    public void notifyObserver();

    public void operation();

}
