package com.alibaba.annotation;

import java.lang.annotation.*;

/**
 * Created by sier.pys on 2018/8/16.
 *
 * @Copyright 2018 alibaba.com All Rights Reserved
 * @Vesion v1.0
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
@Documented
@Inherited
public @interface EmbeddedKafka {

}
