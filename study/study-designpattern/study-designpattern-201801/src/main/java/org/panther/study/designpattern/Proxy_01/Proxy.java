package org.panther.study.designpattern.Proxy_01;

/**
 * @Author: Kevin
 * @Description:
 * @Date: Created in 上午1:09 18-3-29
 * @Version:
 */
public class Proxy implements Sourceable{

    private Sourceable sourceable;

    public Proxy(){
        this.sourceable = new Source();
    }

    public void method(){
        before();
        sourceable.method();
        end();
    }

    public void before(){
        System.out.println("before proxy");
    }

    public void end(){
        System.out.println("end proxy");
    }
}
