package org.panther.study.designpattern.Chain_01;

/**
 * @Author: Kevin
 * @Date: Created in 下午1:47 18-4-1
 * @Version:
 * @Description:
 */
public class MyHandler extends AbstractHandler implements Handler {

    private String name;
    public MyHandler(String name){
        this.name = name;
    }
    public void operator() {
        System.out.println(name + "detail!!!");
        if(getHandler()!=null)
            getHandler().operator();
    }
}
