package org.panther.study.designpattern.Command_01;

/**
 * @Author: Kevin
 * @Date: Created in 下午1:54 18-4-1
 * @Version:
 * @Description:
 */
public class MyCommand implements Command {

    private Receiver receiver;

    public MyCommand(Receiver receiver){
        this.receiver = receiver;
    }
    public void exe() {
        receiver.action();
    }
}
