package com.alibaba.spring.annotation;

import java.lang.annotation.*;

/**
 * @author sier.pys 9/21/18
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
public @interface Schedules {
    Schedule[] value();
}
