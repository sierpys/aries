package com.alibaba.aspect;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;

/**
 * @author sier.pys 2019-07-14
 */
@Aspect
public class LogAspect {

    @Pointcut("execution(public * com.alibaba.aop.core.provider..*.*(..))")
    public void pointCut() {

    }

    @Before("pointCut()")
    public void before(JoinPoint joinPoint) {
        System.out.println("before " + joinPoint.getSignature().getName());
    }
}
