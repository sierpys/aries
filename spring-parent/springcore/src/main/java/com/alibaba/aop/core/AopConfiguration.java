package com.alibaba.aop.core;

import com.alibaba.aop.core.provider.Printer;
import com.alibaba.aspect.LogAspect;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

/**
 * @author sier.pys 2019-07-14
 */
@Configuration
@EnableAspectJAutoProxy
public class AopConfiguration {

    @Bean
    public Printer printer() {
        return new Printer();
    }

    @Bean
    public LogAspect logAspect() {
        return new LogAspect();
    }
}
