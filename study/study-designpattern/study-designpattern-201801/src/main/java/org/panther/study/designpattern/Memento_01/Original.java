package org.panther.study.designpattern.Memento_01;

/**
 * @Author: Kevin
 * @Date: Created in 下午10:35 18-4-1
 * @Version:
 * @Description:
 */
public class Original {

    private String value;
    public String getValue(){
        return value;
    }

    public void setValue(String value){
        this.value = value;
    }

    public Original(String value){
        this.value = value;
    }

    public Memento createMemento(){
        return  new Memento(value);
    }

    public void restoreMemento(Memento memento){
        this.value = memento.getValue();
    }
}
