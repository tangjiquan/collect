package org.panther.study.designpattern.Proxy_01;

/**
 * @Author: Kevin
 * @Description:
 * @Date: Created in 上午1:08 18-3-29
 * @Version:
 */
public class Source implements Sourceable {

    public void method(){
        System.out.println("the original method!");
    }
}
