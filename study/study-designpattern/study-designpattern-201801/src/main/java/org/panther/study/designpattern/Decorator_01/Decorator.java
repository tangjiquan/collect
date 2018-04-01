package org.panther.study.designpattern.Decorator_01;

/**
 * @Author: Kevin
 * @Description:
 * @Date: Created in 上午12:35 18-3-29
 * @Version:
 */
public class Decorator implements Sourceable {

    private Sourceable sourceable;

    public Decorator(Sourceable sourceable){
        this.sourceable = sourceable;
    }

    public void method() {
        System.out.println("before decorator!");
        sourceable.method();
        System.out.println("end decorator!");
    }
}
