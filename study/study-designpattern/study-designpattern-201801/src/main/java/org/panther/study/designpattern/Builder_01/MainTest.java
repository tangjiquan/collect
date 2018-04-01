package org.panther.study.designpattern.Builder_01;

/**
 * @Author: Kevin
 * @Description: 建造者模式则是将各种产品集中起来进行管理，用来创建复合对象，所谓复合对象就是指某个类具有不同的属性
 *                  工厂模式关注的是创建单个产品，而建造者模式则关注创建符合对象
 * @Date: Created in 下午11:52 18-2-10
 * @Version:
 */
public class MainTest {

    public void main(String[] args){
        Builder builder = new Builder();
        builder.produceMailSender(10);
    }
}
