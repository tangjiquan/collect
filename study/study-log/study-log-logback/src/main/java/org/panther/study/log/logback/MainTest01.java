package org.panther.study.log.logback;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @Author: Kevin
 * @Date: Created in 下午10:44 18-4-17
 * @Version:
 * @Description:
 */
public class MainTest01 {
	public static void main(String[] args) {
		Logger log = LoggerFactory.getLogger(MainTest01.class);
		log.trace("======trace");
		log.debug("======debug");
		log.info("======info");
		log.warn("======warn");
		log.error("======error");
	}
}
