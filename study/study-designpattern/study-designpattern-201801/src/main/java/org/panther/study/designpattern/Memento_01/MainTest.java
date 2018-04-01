package org.panther.study.designpattern.Memento_01;

/**
 * @Author: Kevin
 * @Date: Created in 下午10:40 18-4-1
 * @Version:
 * @Description:
 * 主要目的是保存一个对象的某个状态，以便在适当的时候恢复对象，个人觉得叫备份模式更形象些，通俗的讲下：假设有原始类A，A中有各种属性，A可以决定需要备份的属性，备忘录类B是用来存储A的一些内部状态，类C呢，就是一个用来存储备忘录的，且只能存储，不能修改等操作
 */
public class MainTest {

    public static void main(String[] args){
        Original original = new Original("ddd");

        Storage storage = new Storage(original.createMemento());
        System.out.println("初始化状态：" + original.getValue());
        original.setValue("sas");
        System.out.println("修改后状态：" + original.getValue());
        original.restoreMemento(storage.getMemento());
        System.out.println("恢复后：" + original.getValue());

    }
}
