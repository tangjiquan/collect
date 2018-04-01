package org.panther.study.designpattern.Builder_01;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author: Kevin
 * @Description:
 * @Date: Created in 下午11:50 18-2-10
 * @Version:
 */
public class Builder {
    private List<Sender> list = new ArrayList<Sender>();

    public void produceMailSender(int count){
        for(int i=0; i<count; i++){
            list.add(new MailSender());
        }
    }

    public void produceSmsSender(int count){
        for(int i=0; i<count; i++){
            list.add(new SmsSender());
        }
    }

}
