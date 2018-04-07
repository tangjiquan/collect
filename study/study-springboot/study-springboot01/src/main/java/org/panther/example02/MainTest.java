package org.panther.example02;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @Author: Kevin
 * @Date: Created in 下午8:48 18-4-7
 * @Version:
 * @Description:
 * 使用@SpringBootApplication指定这是spring boot应用程序
 */

@SpringBootApplication
public class MainTest {

    public static void main(String[] args){
        SpringApplication.run(MainTest.class,args);
    }
}
