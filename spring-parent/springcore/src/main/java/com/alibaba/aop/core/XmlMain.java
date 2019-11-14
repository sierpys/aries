package com.alibaba.aop.core;

import com.sun.org.apache.bcel.internal.generic.LADD;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.core.scheduler.Schedulers;

import javax.swing.plaf.IconUIResource;
import java.util.concurrent.CountDownLatch;
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
        Mono.fromCallable(() -> getStringSync()).subscribeOn(Schedulers.elastic()).subscribe(System.out::println);
        Thread.sleep(10000);

    }
}
