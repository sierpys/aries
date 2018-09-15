package com.alibaba.akka;

import akka.actor.ActorRef;
import akka.actor.ActorSystem;
import akka.actor.Props;

/**
 * @author sier.pys 9/14/18
 */
public class Main {
//    public static void main(String[] args) {
//        akka.Main.main(new String[]{Akka.class.getName()});
//    }

    public static void main(String[] args) {
        ActorSystem actorSystem = ActorSystem.create("AKKA");
        ActorRef actorRef = actorSystem.actorOf(Props.create(Akka.class), "akka");
        System.out.println(actorRef.path());
    }
}
