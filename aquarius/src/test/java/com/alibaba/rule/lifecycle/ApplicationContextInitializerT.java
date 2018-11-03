package com.alibaba.rule.lifecycle;

import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextInitializer;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.core.env.ConfigurableEnvironment;

/**
 * Created by sier.pys on 2018/9/16.
 *
 * @Copyright 2018 alibaba.com All Rights Reserved
 * @Vesion v1.0
 */
public class ApplicationContextInitializerT implements ApplicationContextInitializer<ConfigurableApplicationContext> {
    @Override
    public void initialize(ConfigurableApplicationContext applicationContext) {
        String system = System.getProperty("os.name");
        String os = system.toLowerCase().startsWith("mac") ? "mac" : "window";
        ConfigurableEnvironment environment = applicationContext.getEnvironment();
        environment.setActiveProfiles(os);

    }
}
