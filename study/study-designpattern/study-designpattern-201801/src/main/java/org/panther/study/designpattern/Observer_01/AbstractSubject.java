package org.panther.study.designpattern.Observer_01;

import java.util.Enumeration;
import java.util.Vector;

/**
 * @Author: Kevin
 * @Date: Created in 下午1:13 18-4-1
 * @Version:
 * @Description:
 */
public abstract class AbstractSubject implements  Subject{

    private Vector<Observer>  vector = new Vector<Observer>();

    public void add(Observer observer) {
        vector.add(observer);
    }

    public void del(Observer observer) {
        vector.remove(observer);
    }

    public void notifyObserver() {
        Enumeration<Observer> objs = vector.elements();
        while(objs.hasMoreElements()){
            objs.nextElement().update();
        }
    }
}
