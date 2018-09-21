package com.alibaba.spring.config;

import com.alibaba.spring.S2;
import com.alibaba.spring.Service;
import com.alibaba.spring.impl.S2Impl;
import com.alibaba.spring.impl.ServiceImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author sier.pys 9/15/18
 */
//@Configuration
public class Config {
    @Bean
    public Service getService() {
        return new ServiceImpl();
    }

//    @Bean(name = "s2")
//    public S2 getS2(Service service) {
//        S2Impl s2 = new S2Impl();
//        s2.setService(service);
//        return s2;
//    }
}
