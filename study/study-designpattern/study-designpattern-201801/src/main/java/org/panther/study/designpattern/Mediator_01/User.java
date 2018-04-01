package org.panther.study.designpattern.Mediator_01;

/**
 * @Author: Kevin
 * @Date: Created in 下午2:25 18-4-1
 * @Version:
 * @Description:
 */
public abstract class User {

    private Mediator mediator;

    public Mediator getMediator(){
        return mediator;
    }

    public User(Mediator mediator){
        this.mediator = mediator;
    }

    public abstract void work();
}
