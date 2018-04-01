package org.panther.study.designpattern.Strategy_01;

/**
 * @Author: Kevin
 * @Date: Created in 下午12:37 18-4-1
 * @Version:
 * @Description:
 */
public class Multiply extends AbstractCalcultor implements ICalculator {

    public int calculate(String exp) {
        int[] arrayInt = split(exp, "\\*");
        return arrayInt[0] * arrayInt[1];
    }
}
