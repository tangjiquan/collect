package org.panther.study.designpattern.TemplateMethod_01;

/**
 * @Author: Kevin
 * @Date: Created in 下午12:44 18-4-1
 * @Version:
 * @Description:
 */
public abstract class AbstractCalculator {

    public final int  calculate(String exp, String opt){
        int array[] = split(exp, opt);
        return calculte(array[0], array[1]);
    }

    public int[] split(String exp, String opt){
        String array[] = exp.split(opt);
        int arr[] = new int[2];
        arr[0] = Integer.valueOf(array[0]);
        arr[1] = Integer.valueOf(array[1]);
        return arr;
    }

    abstract  public int calculte(int num1, int num2);
}
