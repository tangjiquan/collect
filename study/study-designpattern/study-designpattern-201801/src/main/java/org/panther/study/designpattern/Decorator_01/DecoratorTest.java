package org.panther.study.designpattern.Decorator_01;

/**
 * @Author: Kevin
 * @Description:
 * @Date: Created in 上午12:38 18-3-29
 * @Version:
 */
public class DecoratorTest {

    public static void main(String[] args){
        Sourceable sourceable = new Source();
        Decorator decorator = new Decorator(sourceable);
        decorator.method();
    }
}
