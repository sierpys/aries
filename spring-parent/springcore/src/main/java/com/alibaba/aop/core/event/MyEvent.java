package com.alibaba.aop.core.event;

import org.springframework.context.ApplicationEvent;

/**
 * @author sier.pys 2019/11/16
 */
public class MyEvent extends ApplicationEvent {
    private String message;

    /**
     * Create a new ApplicationEvent.
     *
     * @param source the object on which the event initially occurred (never {@code null})
     */
    public MyEvent(Object source, String message) {
        super(source);
        this.message = message;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

}
