package com.alibaba.lifecycle;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author sier.pys 8/17/18
 */
@Configuration
public class Config {
    @Bean
    public SpringBean springBean() {
        return new SpringBean();
    }
}
