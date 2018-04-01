package org.panther.study.designpattern.Interpreter_01;

/**
 * @Author: Kevin
 * @Date: Created in 下午2:09 18-4-1
 * @Version:
 * @Description:
 */
public class MainTest {

    public static void main(String[] args){
        int result = new Minus().interpret(new Context(new Plus().interpret(new Context(2,3)), 4));
        System.out.println(result);
    }
}
