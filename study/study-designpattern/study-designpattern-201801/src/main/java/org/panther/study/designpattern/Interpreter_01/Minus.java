package org.panther.study.designpattern.Interpreter_01;

/**
 * @Author: Kevin
 * @Date: Created in 下午2:08 18-4-1
 * @Version:
 * @Description:
 */
public class Minus implements Expression {

    public int interpret(Context context) {
        return context.getNum1() * context.getNum2();
    }
}
