package org.panther.study.designpattern.State_01;

/**
 * @Author: Kevin
 * @Date: Created in 下午10:21 18-4-1
 * @Version:
 * @Description:
 */
public class Context {

    private State state;

    public State getState() {
        return state;
    }

    public void setState(State state) {
        this.state = state;
    }

    public Context(State state){
        this.state = state;
    }

    public void method(){
        if(state.getValue().equals("state1")){
            state.method1();
        }else if(state.getValue().equals("state2")){
            state.method2();
        }
    }
}
