package org.panther.study.designpattern.Interpreter_01;

/**
 * @Author: Kevin
 * @Date: Created in 下午2:06 18-4-1
 * @Version:
 * @Description:
 */
public interface Expression {
    public int interpret(Context context);
}
