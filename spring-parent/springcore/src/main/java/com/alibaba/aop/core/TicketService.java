package com.alibaba.aop.core;

/**
 * @author sier.pys 10/19/18
 */
public interface TicketService {
    //售票
    void sellTicket();

    //问询
    void inquire();

    //退票
    void withdraw();
}
