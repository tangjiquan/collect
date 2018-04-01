package org.panther.study.designpattern.State_01;

/**
 * @Author: Kevin
 * @Date: Created in 下午10:23 18-4-1
 * @Version:
 * @Description:
 * 当对象的状态改变时，同时改变其行为，很好理解！就拿QQ来说，有几种状态，在线、隐身、忙碌等，每个状态对应不同的操作，而且你的好友也能看到你的状态，所以，状态模式就两点：1、可以通过改变状态来获得不同的行为。2、你的好友能同时看到你的变化。
 */
public class MainTest {

    public static void main(String []args){
        State state = new State();
        Context context = new Context(state);

        state.setValue("state1");
        context.method();

        state.setValue("state2");
        context.method();
    }
}
