package org.panther.study.designpattern.Iterator_01;

/**
 * @Author: Kevin
 * @Date: Created in 下午1:31 18-4-1
 * @Version:
 * @Description:
 */
public class MyCollection implements   Collection {

    public String string[] = {"A", "B", "C"};

    public Iterator iterator() {
        return new MyIterator(this);
    }

    public Object get(int i) {
        return  string[i];
    }

    public int size() {
        return string.length;
    }
}
