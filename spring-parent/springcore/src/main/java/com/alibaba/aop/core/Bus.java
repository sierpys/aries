package com.alibaba.aop.core;

import org.springframework.stereotype.Component;

/**
 * @author sier.pys 10/21/18
 */
@Component
public class Bus implements TicketService {

    public Bus() {
        System.out.println("bus ....");
    }

    @Override
    public void sellTicket() {
        System.out.println("bus");
    }

    @Override
    public void inquire() {

    }

    @Override
    public void withdraw() {

    }
}
