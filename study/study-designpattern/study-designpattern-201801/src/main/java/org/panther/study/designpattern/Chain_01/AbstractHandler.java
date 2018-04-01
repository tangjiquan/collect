package org.panther.study.designpattern.Chain_01;

/**
 * @Author: Kevin
 * @Date: Created in 下午1:45 18-4-1
 * @Version:
 * @Description:
 */
public abstract class AbstractHandler  {
    private Handler handler;

    public Handler getHandler() {
        return handler;
    }

    public void setHandler(Handler handler) {
        this.handler = handler;
    }
}
