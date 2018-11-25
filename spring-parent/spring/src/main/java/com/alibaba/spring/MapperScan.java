package com.alibaba.spring;

import org.springframework.context.annotation.Import;

import java.lang.annotation.*;

/**
 * @author sier.pys 11/21/18
 */
@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.TYPE})
@Import(MapperScannerBeanRegister.class)
public @interface MapperScan {

}
