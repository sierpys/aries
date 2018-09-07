package com.alibaba.rule.timeout;

import org.junit.Rule;
import org.junit.rules.Timeout;

import java.util.concurrent.TimeUnit;

/**
 * @author sier.pys 8/31/18
 */
public class TimeOut {
    @Rule
    public final static Timeout globalTimeOut = new Timeout(12000, TimeUnit.MILLISECONDS);

}
