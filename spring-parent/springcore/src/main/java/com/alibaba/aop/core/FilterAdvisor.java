package com.alibaba.aop.core;

import org.aopalliance.aop.Advice;
import org.springframework.aop.Pointcut;
import org.springframework.aop.support.AbstractPointcutAdvisor;

/**
 * @author sier.pys 10/22/18
 */
public class FilterAdvisor extends AbstractPointcutAdvisor {
    private Pointcut pointcut;
    private Advice advice;

    public FilterAdvisor(Pointcut pointcut, Advice advice) {
        super();
        this.pointcut = pointcut;
        this.advice = advice;
    }

    @Override
    public Pointcut getPointcut() {
        return pointcut;
    }

    @Override
    public Advice getAdvice() {
        return advice;
    }
}
