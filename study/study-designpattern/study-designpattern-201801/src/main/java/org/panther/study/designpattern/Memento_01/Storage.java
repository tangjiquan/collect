package org.panther.study.designpattern.Memento_01;

/**
 * @Author: Kevin
 * @Date: Created in 下午10:38 18-4-1
 * @Version:
 * @Description:
 */
public class Storage {

    private Memento memento;

    public Memento getMemento() {
        return memento;
    }

    public void setMemento(Memento memento) {
        this.memento = memento;
    }

    public Storage(Memento memento){
        this.memento = memento;
    }
}
