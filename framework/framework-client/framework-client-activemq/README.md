##### ActiveMQ #####
## 1.activemq概述
    多种语言和协议编写客户端。语言: Java, C, C++, C#, Ruby, Perl, Python, PHP。应用协议: OpenWire,Stomp REST,WS Notification,XMPP,AMQP
    完全支持JMS1.1和J2EE 1.4规范 (持久化,XA消息,事务)
    对Spring的支持,ActiveMQ可以很容易内嵌到使用Spring的系统里面去,而且也支持Spring2.0的特性
    通过了常见J2EE服务器(如 Geronimo,JBoss 4, GlassFish,WebLogic)的测试,其中通过JCA 1.5 resource adaptors的配置,可以让ActiveMQ可以自动的部署到任何兼容J2EE 1.4 商业服务器上
    支持多种传送协议:in-VM,TCP,SSL,NIO,UDP,JGroups,JXTA
    支持通过JDBC和journal提供高速的消息持久化
    从设计上保证了高性能的集群,客户端-服务器,点对点
    支持Ajax
    支持与Axis的整合
    可以很容易得调用内嵌JMS provider,进行测试

## 2.activemq使用
    ./activemq start
    ./activemq stop

    消息信息查看
    http://127.0.0.1:8161/admin/
        默认账号：admin
        密码：admin

## 3.activemq目录结构
    bin存放的是脚本文件
    conf存放的是基本配置文件
    data存放的是日志文件
    docs存放的是说明文档
    examples存放的是简单的实例
    lib存放的是activemq所需jar包
    webapps用于存放项目的目录

## 4. 案例


## 5. ActiveMQ使用场景
    > 多个项目之间集成
        (1) 跨平台
        (2) 多语言
        (3) 多项目
    > 降低系统间模块的耦合度，解耦
        (1) 软件扩展性
    > 系统前后端隔离
        (1) 前后端隔离，屏蔽高安全区
