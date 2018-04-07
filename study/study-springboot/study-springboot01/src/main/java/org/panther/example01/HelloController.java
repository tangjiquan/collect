package org.panther.example01;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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

    @RequestMapping("/hello")
    public String hello(){
        return "hello22";
    }
}
