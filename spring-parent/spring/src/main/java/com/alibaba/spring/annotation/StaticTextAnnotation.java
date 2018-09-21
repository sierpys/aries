package com.alibaba.spring.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @author sier.pys 9/21/18
 */
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.METHOD})
public @interface StaticTextAnnotation {
    String text() default "Default text for static text annotation";

    String value() default "Default value";
}
