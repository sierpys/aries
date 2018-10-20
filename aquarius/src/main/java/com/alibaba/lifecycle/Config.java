package com.alibaba.lifecycle;

import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

/**
 * @author sier.pys 8/17/18
 */
@Configuration
//@EnableScheduling
//@EnableCaching
@EnableAspectJAutoProxy(exposeProxy = true, proxyTargetClass = true)
public class Config {
    @Bean
    public SpringBean springBean() {
        return new SpringBean();
    }


    @Scheduled(fixedRate = 1)
    public void doSom() {
        System.out.println(".....");
    }
}
