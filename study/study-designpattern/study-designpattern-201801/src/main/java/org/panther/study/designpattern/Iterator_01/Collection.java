package org.panther.study.designpattern.Iterator_01;

/**
 * @Author: Kevin
 * @Date: Created in 下午1:21 18-4-1
 * @Version:
 * @Description:
 */
public interface Collection {

    public Iterator iterator();

    public Object get(int i);

    public int size();
}
