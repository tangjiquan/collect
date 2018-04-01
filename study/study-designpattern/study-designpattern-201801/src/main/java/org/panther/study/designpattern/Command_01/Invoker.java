package org.panther.study.designpattern.Command_01;

/**
 * @Author: Kevin
 * @Date: Created in 下午1:56 18-4-1
 * @Version:
 * @Description:
 */
public class Invoker {

    private Command command;
    public Invoker(Command command){
        this.command = command;
    }

    public void action(){
        this.command.exe();
    }
}
