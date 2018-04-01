package org.panther.study.designpattern.Bridge_01;

/**
 * @Author: Kevin
 * @Description:
 * @Date: Created in 上午1:24 18-3-29
 * @Version:
 */
public abstract class Bridge {

    private Sourceable source;

    public void method(){
        source.method();
    }

    public Sourceable getSource() {
        return source;
    }

    public void setSource(Sourceable source) {
        this.source = source;
    }
}
