package org.panther.study.designpattern.Observer_01;

/**
 * @Author: Kevin
 * @Date: Created in 下午1:17 18-4-1
 * @Version:
 * @Description:
 * MySubject类就是我们的主对象，Observer1和Observer2是依赖于MySubject的对象，当MySubject变化时，Observer1和Observer2必然变化。AbstractSubject类中定义着需要监控的对象列表，可以对其进行修改：增加或删除被监控对象，且当MySubject变化时，负责通知在列表内存在的对象。
 */
public class ObserverTest {

    public static void main(String[] args){
        Subject subject = new MySubject();
        subject.add(new Observer1());
        subject.add(new Observer2());
        subject.operation();
    }
}
