package com.alibaba.aop.core;

/**
 * @author sier.pys 10/19/18
 */
public class RailwayStation implements TicketService {
    @Override
    public void sellTicket() {
        System.out.println("��Ʊ............");
        throw new RuntimeException("....");
    }

    @Override
    public void inquire() {
        System.out.println("��ѯ.............");
    }

    @Override
    public void withdraw() {
        System.out.println("��Ʊ.............");
    }

}
