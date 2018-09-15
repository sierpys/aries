package com.alibaba.akka.box;

import akka.actor.ActorRef;
import akka.actor.ActorSystem;
import akka.actor.Props;
import akka.actor.Terminated;
import com.typesafe.config.ConfigFactory;
import scala.concurrent.duration.Duration;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

/**
 * @author sier.pys 9/14/18
 */
public class Main {
    public static void main(String[] args) {
        ActorSystem actorSystem = ActorSystem.create("Inbox", ConfigFactory.load("akka.conf"));

        ActorRef actorRef = actorSystem.actorOf(Props.create(Inbox.class), "inbox");

        akka.actor.Inbox inbox = akka.actor.Inbox.create(actorSystem);
        inbox.watch(actorRef);


        inbox.send(actorRef, Inbox.MSG.WORKING);
        inbox.send(actorRef, Inbox.MSG.DONE);
        inbox.send(actorRef, Inbox.MSG.CLONE);


        for (; ; ) {
            try {
                Object receive = inbox.receive(Duration.create(1, TimeUnit.SECONDS));
                if (receive == Inbox.MSG.CLONE) {
                    System.out.println("Inbox text actor is closing");
                } else if (receive instanceof Terminated) {
                    System.out.println("shut down");
                    actorSystem.shutdown();
                } else {
                    System.out.println(receive);
                }

            } catch (TimeoutException e) {
                e.printStackTrace();
            }
        }
    }
}
