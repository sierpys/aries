package com.alibaba.akka.box;

import akka.actor.UntypedActor;
import akka.event.Logging;
import akka.event.LoggingAdapter;

/**
 * @author sier.pys 9/14/18
 */
public class Inbox extends UntypedActor {

    private final LoggingAdapter log = Logging.getLogger(getContext().system(), this);

    public enum MSG {
        WORKING, DONE, CLONE,
        ;
    }

    @Override
    public void onReceive(Object o) throws Throwable {
        if (o == MSG.WORKING) {
            log.info("I am a woking actor ref");
        } else if (o == MSG.DONE) {
            log.info("I am done");
        } else if (o == MSG.CLONE) {
            System.out.println("I am closed");
            getSender().tell(MSG.CLONE, getSelf());
            getContext().stop(getSelf());
        } else {
            unhandled(o);
        }
    }
}
