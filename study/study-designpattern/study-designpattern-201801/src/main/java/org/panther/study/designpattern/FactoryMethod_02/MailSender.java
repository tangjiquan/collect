package org.panther.study.designpattern.FactoryMethod_02;

/**
 * @Author: Kevin
 * @Description:
 * @Date: Created in 下午10:40 18-2-6
 * @Version:
 */
public class MailSender implements Sender {
    @Override
    public void send() {
        System.out.println("mailsender..");
    }
}
