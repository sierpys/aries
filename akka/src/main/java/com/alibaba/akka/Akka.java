package com.alibaba.akka;

import akka.actor.ActorRef;
import akka.actor.Props;
import akka.actor.UntypedActor;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author sier.pys 9/14/18
 */
public class Akka extends UntypedActor {
    ThreadLocal<AtomicInteger> threadLocal = ThreadLocal.withInitial(() -> new AtomicInteger(0));


    final static GsonBuilder builder = new GsonBuilder();

    @Override
    public void preStart() throws Exception {
        final ActorRef actorRef = getContext().actorOf(Props.create(Greeter.class), "greeter");
//        actorRef.tell(Greeter.MSG.GREET, getSelf());
        Message message = new Message();
        message.list.add(1);
        message.list.add(2);
        message.id = 9999;
        actorRef.tell(message, getSelf());
    }

    @Override
    public void onReceive(Object o) throws Throwable {
        AtomicInteger atomicInteger = threadLocal.get();
        atomicInteger.getAndAdd(1);
        Gson gson = builder.create();
        if (o instanceof Message) {
            System.out.println("<<<<<<<<<<<<< message >>>>>>>>>>>>");
            System.out.println(gson.toJson(o));
            getContext().stop(getSelf());
        }
        if (o == Greeter.MSG.DONE) {
            System.out.println("<<<<<<<<<<<<<<<>>>>>>>>>>>>");
            getContext().stop(getSelf());
        } else {
            unhandled(o);
        }
    }
}
