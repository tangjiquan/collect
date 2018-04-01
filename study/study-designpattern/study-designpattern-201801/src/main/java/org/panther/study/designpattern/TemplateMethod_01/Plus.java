package org.panther.study.designpattern.TemplateMethod_01;

/**
 * @Author: Kevin
 * @Date: Created in 下午12:48 18-4-1
 * @Version:
 * @Description:
 */
public class Plus extends AbstractCalculator {

    @Override
    public int calculte(int num1, int num2) {
        return  num1 + num2;
    }
}
