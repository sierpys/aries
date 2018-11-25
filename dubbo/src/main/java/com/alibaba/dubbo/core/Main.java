package com.alibaba.dubbo.core;

import com.alibaba.dubbo.config.ApplicationConfig;
import com.alibaba.dubbo.config.RegistryConfig;
import com.alibaba.dubbo.config.ServiceConfig;
import com.alibaba.dubbo.core.impl.DemoServiceImpl;

import java.io.IOException;

/**
 * @author sier.pys 11/4/18
 */
public class Main {
    public static void main(String[] args) throws IOException {
        ServiceConfig<DemoService> serviceConfig = new ServiceConfig<>();
        serviceConfig.setApplication(new ApplicationConfig("dubbo-provider"));
        serviceConfig.setRegistry(new RegistryConfig("zookeeper://127.0.0.1:2181"));
        serviceConfig.setInterface(DemoService.class);
        serviceConfig.setRef(new DemoServiceImpl());

        serviceConfig.export();

        System.in.read();
    }
}
