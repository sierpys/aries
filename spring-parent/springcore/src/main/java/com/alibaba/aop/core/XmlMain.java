package com.alibaba.aop.core;

import com.alibaba.aop.core.event.MyEvent;
import com.alibaba.aop.core.event.TypeEvent;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
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
        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext(DataProvider.class);
        AutoWire autoWire = applicationContext.getBean(AutoWire.class);
        applicationContext.publishEvent(new MyEvent(new Object(), "alibaba"));
        applicationContext.publishEvent(new MyEvent(new TypeEvent(), "alibaba_____888888"));
        autoWire.print();
    }
}
