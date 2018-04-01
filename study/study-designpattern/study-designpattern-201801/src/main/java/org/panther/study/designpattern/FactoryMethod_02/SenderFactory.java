package org.panther.study.designpattern.FactoryMethod_02;

/**
 * @Author: Kevin
 * @Description:
 * @Date: Created in 下午10:41 18-2-6
 * @Version:
 */
public class SenderFactory {

    public Sender produceMail(){
        return new MailSender();
    }

    public Sender produceSms(){
        return new SmsSender();
    }
}
