package org.panther.example03;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;

/**
 * @Author: Kevin
 * @Date: Created in 下午8:43 18-4-7
 * @Version:
 * @Description:
 *
 * @RestController 等价于@Controller 和@ResponseBody
 */

@RestController
public class HelloController {

    /**
     * spring boot默认使用的json框架是jackson
     * @return
     */
    @RequestMapping("/getPerson2")
    public Person getPerson(){
        Person person = new Person();
        person.setId("11");
        person.setCreateTime(new Date());
        person.setName("zhangsan");
        person.setRemarks("dd");
        return person;
    }
}
