package org.panther.study.concurrency;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 课程里标记【推荐】的类的写法
 *
 * @author: Kevin
 * @date: created in 下午10:27 2018-07-07
 * @version: V1.0
 */

@Target(ElementType.TYPE)
@Retention(RetentionPolicy.SOURCE)
public @interface Recomment {

	String value() default  "";
}
