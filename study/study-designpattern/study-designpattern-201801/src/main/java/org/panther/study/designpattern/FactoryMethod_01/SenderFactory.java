package org.panther.study.designpattern.FactoryMethod_01;

/**
 * @Author: Kevin
 * @Description:
 * @Date: Created in 下午10:41 18-2-6
 * @Version:
 */
public class SenderFactory {

    public Sender produce(String type){
        if("mail".equals(type)){
            return new MailSender();
        }else if("sms".equals(type)){
            return new SmsSender();
        }else{
            System.out.println("error....");
            return null;
        }
    }
}
