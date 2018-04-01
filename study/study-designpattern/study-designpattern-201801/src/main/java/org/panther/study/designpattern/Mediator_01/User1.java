package org.panther.study.designpattern.Mediator_01;

/**
 * @Author: Kevin
 * @Date: Created in 下午2:27 18-4-1
 * @Version:
 * @Description:
 */
public class User1 extends User {

    public User1(Mediator mediator){
        super(mediator);
    }

    @Override
    public void work() {
        System.out.println("User1 work...");
    }
}
