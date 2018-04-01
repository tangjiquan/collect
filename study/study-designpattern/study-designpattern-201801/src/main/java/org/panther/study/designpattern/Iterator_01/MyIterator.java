package org.panther.study.designpattern.Iterator_01;

/**
 * @Author: Kevin
 * @Date: Created in 下午1:32 18-4-1
 * @Version:
 * @Description:
 */
public class MyIterator implements Iterator {

    private Collection collection;
    private int  pos = -1;
    public MyIterator(Collection collection){
        this.collection = collection;
    }
    public Object previous() {
        if(pos >0){
            pos--;
        }
        return collection.get(pos);
    }

    public Object next() {
        if(pos<collection.size()-1){
            pos++;
        }
        return collection.get(pos);
    }

    public boolean hasnext() {
        if(pos<collection.size()-1){
            return true;
        }else{
            return false;
        }

    }

    public Object fisrt() {
        pos = 0;
        return collection.get(pos);
    }
}
