package org.panther.study.springboot.example02;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @author: Kevin
 * @date: created in 下午9:09 2018-07-08
 * @version: V1.0
 */
/*
将项目以war包的形式发布到tomat上

@SpringBootApplication
public class HotDeployApplication extends SpringBootServletInitializer{

	@Override
	protected SpringApplicationBuilder configure(SpringApplicationBuilder builder) {
		return builder.sources(HotDeployApplication.class);
	}

	public static void main(String[] args){
		SpringApplication.run(HotDeployApplication.class);
	}
}
*/

/**/
@SpringBootApplication
public class HotDeployApplication {

	public static void main(String[] args){
		SpringApplication.run(HotDeployApplication.class);
	}
}

