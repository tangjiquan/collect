package com.panther.study.dubbo.lesson01.chapter06.provider.impl;

import com.panther.study.dubbo.lesson01.chapter06.entity.Simple;
import com.panther.study.dubbo.lesson01.chapter06.provider.SimpleService;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;


@Service("simpleService")
@com.alibaba.dubbo.config.annotation.Service(interfaceClass=com.panther.study.dubbo.lesson01.chapter06.provider.SimpleService.class, protocol={"dubbo"}, retries=0)
public class SimpleServiceImpl implements SimpleService {

	@Override
	public String sayHello(String name) {
		return "hello" + name;
	}

	@Override
	public Simple getSimple() {
        Map<String,Integer> map = new HashMap<String, Integer>(2);  
        map.put("zhang0", 1);  
        map.put("zhang1", 2);  
        return new Simple("zhang3", 21, map);
	}

}
