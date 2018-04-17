package org.panther.study.log.logback;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @Author: Kevin
 * @Date: Created in 下午11:29 18-4-17
 * @Version:
 * @Description:
 */
public class MainTestPrd {
	static Logger  log = LoggerFactory.getLogger(MainTest01.class);

	public static void main(String[] args){

		log.trace("======trace");
		log.debug("======debug");
		log.info("======info");
		log.warn("======warn");
		log.error("======error");
	}
}
