package com.alibaba.akka;

import akka.actor.UntypedActor;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.concurrent.TimeUnit;

/**
 * @author sier.pys 9/14/18
 */
public class Greeter extends UntypedActor {
    final static GsonBuilder builder = new GsonBuilder();

    public static enum MSG {
        GREET, DONE,
        ;
    }

    @Override
    public void onReceive(Object o) throws Throwable {
        if (o instanceof Message) {
            System.out.println("<<<<<<<<<< message >>>>>>>>>>>>");
            Gson gson = builder.create();
            System.out.println(gson.toJson(o));

            Message message = (Message) o;
            message.list.add(8888888);
            getSender().tell(message, getSelf());
        }
        if (o == MSG.GREET) {
            System.out.println("<<<<<<<<<<<<<<>>>>>>>>>>>>>>");
            TimeUnit.SECONDS.sleep(1);
            getSender().tell(MSG.DONE, getSelf());
        } else {
            unhandled(o);
        }
    }
}
