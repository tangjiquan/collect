package org.panther.study.designpattern.AbstractFactory_01;

/**
 * @Author: Kevin
 * @Description:
 * @Date: Created in 下午11:01 18-2-6
 * @Version:
 */
public class MailProvider implements Provider {

    @Override
    public Sender produce() {
        return new MailSender();
    }
}
