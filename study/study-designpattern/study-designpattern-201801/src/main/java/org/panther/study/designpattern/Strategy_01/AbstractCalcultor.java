package org.panther.study.designpattern.Strategy_01;

/**
 * @Author: Kevin
 * @Date: Created in 下午12:35 18-4-1
 * @Version:
 * @Description:
 */
public abstract class AbstractCalcultor {

    public int[] split(String exp, String opt){
        String array[] = exp.split(opt);
        int arr[] = new int[2];
        arr[0] = Integer.valueOf(array[0]);
        arr[1] = Integer.valueOf(array[1]);
        return arr;
    }
}
