package com.alibaba.akka.router;

import akka.actor.*;
import akka.routing.ActorRefRoutee;
import akka.routing.RoundRobinRoutingLogic;
import akka.routing.Routee;
import akka.routing.Router;
import com.alibaba.akka.box.Inbox;
import com.typesafe.config.ConfigFactory;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicBoolean;

/**
 * @author sier.pys 9/14/18
 */
public class RouterTest extends UntypedActor {
    public Router router;

    public  static AtomicBoolean flag = new AtomicBoolean(true);

    {
        List<Routee> routees = new ArrayList<>();
        for (int i = 0; i < 5; i++) {
            ActorRef worker = getContext().actorOf(Props.create(Inbox.class), "worker_" + i);

            getContext().watch(worker);

            routees.add(new ActorRefRoutee(worker));
        }

        router = new Router(new RoundRobinRoutingLogic(), routees);
    }

    @Override
    public void onReceive(Object o) throws Throwable {
        if (o instanceof Inbox.MSG) {
            router.route(o, getSender());
        } else if (o instanceof Terminated) {
            router.removeRoutee(((Terminated) o).actor());
            System.out.println(((Terminated) o).actor().path() + " 该actor已经删除。router.size=" + router.routees().size());
            if(router.routees().size() == 0){//没有可用actor了
                System.out.print("没有可用actor了，系统关闭。");
                flag.compareAndSet(true, false);
                getContext().system().shutdown();
            }
        }
    }
    public static void main(String[] args) throws InterruptedException {
        ActorSystem system = ActorSystem.create("strategy", ConfigFactory.load("akka.config"));
        ActorRef routerTest = system.actorOf(Props.create(RouterTest.class), "RouterTest");

        int i = 1;
        while(flag.get()){
            routerTest.tell(Inbox.MSG.WORKING, ActorRef.noSender());

            if(i % 10 == 0) routerTest.tell(Inbox.MSG.CLONE, ActorRef.noSender());
            Thread.sleep(500);

            i ++;
        }
    }

}
