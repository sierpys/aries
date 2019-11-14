package com.alibaba.aop.core;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import reactor.core.publisher.Flux;

import java.util.concurrent.TimeUnit;

/**
 * @author sier.pys 10/27/18
 */
public class XmlMain {
    private static Flux<String> getZipDescFlux() {
        String desc = "Zip two sources together, that is to say wait for all the sources to emit one element and combine these elements once into a Tuple2.";
        return Flux.fromArray(desc.split("\\s+"));  // 1
    }

    private static String getStringSync() {
        try {
            TimeUnit.SECONDS.sleep(2);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return "Hello, Reactor!";
    }

    public static void main(String[] args) throws InterruptedException {
        ApplicationContext applicationContext = new ClassPathXmlApplicationContext("application.xml");
        SourceDataProvider provider = applicationContext.getBean(SourceDataProvider.class);
        provider.printSource();
    }
}
