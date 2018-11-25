package com.alibaba.aop.core;

import org.aopalliance.aop.Advice;
import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;
import org.springframework.aop.aspectj.AspectJExpressionPointcut;
import org.springframework.aop.framework.ProxyFactoryBean;
import org.springframework.util.ObjectUtils;

/**
 * @author sier.pys 10/19/18
 */
public class TicketServiceAroundAdvice implements MethodInterceptor {


    @Override
    public Object invoke(MethodInvocation invocation) throws Throwable {
        System.out.println("AROUND_ADVICE:BEGIN....");
        Object returnValue = invocation.proceed();
        System.out.println("AROUND_ADVICE:END.....");
        return returnValue;

    }


    public static void main(String[] args) {
        Advice beforeAdvice = new TicketServiceBeforeAdvice();
//        Advice afterReturningAdvice = new TicketServiceAfterReturningAdvice();
//        Advice aroundAdvice = new TicketServiceAroundAdvice();
//        Advice throwsAdvice = new TicketServiceThrowsAdvice();


        RailwayStation railwayStation = new RailwayStation();


        ProxyFactoryBean proxyFactoryBean = new ProxyFactoryBean();
        proxyFactoryBean.setInterfaces(TicketService.class);
        proxyFactoryBean.setTarget(railwayStation);
        proxyFactoryBean.setProxyTargetClass(true);
//        proxyFactoryBean.addAdvice(beforeAdvice);
//        proxyFactoryBean.addAdvice(afterReturningAdvice);
//        proxyFactoryBean.addAdvice(aroundAdvice);
//        proxyFactoryBean.addAdvice(throwsAdvice);

//        AspectJExpressionPointcut pointcut = new AspectJExpressionPointcut();
//        pointcut.setExpression("execution( * sellTicket(..))");
//        proxyFactoryBean.addAdvisor(new FilterAdvisor(pointcut, beforeAdvice));

//        proxyFactoryBean.setOptimize(true);

        proxyFactoryBean.addAdvice(beforeAdvice);
        TicketService ticketService = (TicketService) proxyFactoryBean.getObject();
        if (ObjectUtils.isEmpty(ticketService)) {
            return;
        }
        ticketService.sellTicket();
        ticketService.inquire();

    }
}
