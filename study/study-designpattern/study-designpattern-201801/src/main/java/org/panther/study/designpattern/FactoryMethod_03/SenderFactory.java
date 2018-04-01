package org.panther.study.designpattern.FactoryMethod_03;

/**
 * @Author: Kevin
 * @Description:
 * @Date: Created in 下午10:41 18-2-6
 * @Version:
 */
public class SenderFactory {

    public static Sender produceMail(){
        return new MailSender();
    }

    public static Sender produceSms(){
        return new SmsSender();
    }
}
