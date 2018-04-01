package org.panther.study.designpattern.Command_01;

/**
 * @Author: Kevin
 * @Date: Created in 下午1:57 18-4-1
 * @Version:
 * @Description:
 * 命令模式的目的就是达到命令的发出者和执行者之间解耦，实现请求和执行分开
 * 举个例子，司令员下令让士兵去干件事情，从整个事情的角度来考虑，司令员的作用是，发出口令，口令经过传递，传到了士兵耳朵里，士兵去执行。这个过程好在，三者相互解耦，任何一方都不用去依赖其他人，只需要做好自己的事儿就行，司令员要的是结果，不会去关注到底士兵是怎么实现的
 */
public class MainTest {

    public static void main(String[] args){
        Receiver receiver = new Receiver();
        Command command = new MyCommand(receiver);
        Invoker invoker = new Invoker(command);
        invoker.action();
    }
}
