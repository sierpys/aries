package com.alibaba.lifecycle;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.context.annotation.Profile;
import org.springframework.scheduling.annotation.Scheduled;
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

    @Bean
    @Profile("windows")
    public Service windows() {
        return new TServiceImpl();
    }

    @Bean
    @Profile("mac")
    public Service mac() {
        return new MACServiceImpl();
    }

}
