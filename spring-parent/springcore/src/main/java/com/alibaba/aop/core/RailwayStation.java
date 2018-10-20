package com.alibaba.aop.core;

/**
 * @author sier.pys 10/19/18
 */
public class RailwayStation implements TicketService {
    @Override
    public void sellTicket() {
        System.out.println(" €∆±............");
        throw new RuntimeException("....");
    }

    @Override
    public void inquire() {
        System.out.println("Œ —Ø.............");
    }

    @Override
    public void withdraw() {
        System.out.println("ÕÀ∆±.............");
    }

}
