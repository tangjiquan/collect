package org.panther.study.designpattern.Chain_01;

/**
 * @Author: Kevin
 * @Date: Created in 下午1:48 18-4-1
 * @Version:
 * @Description:
 * ，链接上的请求可以是一条链，可以是一个树，还可以是一个环，模式本身不约束这个，需要我们自己去实现，同时，在一个时刻，命令只允许由一个对象传给另一个对象，而不允许传给多个对象
 * 有多个对象，每个对象持有对下一个对象的引用，这样就会形成一条链，请求在这条链上传递，直到某一对象决定处理该请求。但是发出者并不清楚到底最终那个对象会处理该请求
 */
public class MainTest {

    public static void main(String[] args){
        MyHandler h1 = new MyHandler("h1");
        MyHandler h2 = new MyHandler("h2");
        MyHandler h3 = new MyHandler("h3");
        MyHandler h4 = new MyHandler("h4");

        h1.setHandler(h2);
        h2.setHandler(h3);
        h3.setHandler(h4);

        h1.operator();
    }
}
