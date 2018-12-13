package com.alibaba.aop.core;

import ch.qos.logback.classic.pattern.ClassicConverter;
import ch.qos.logback.classic.spi.ILoggingEvent;

/**
 * @author sier.pys 12/13/18
 */
public class TraceIdConverter extends ClassicConverter {
    @Override
    public String convert(ILoggingEvent event) {
        return "00000000011111110000000000";
    }
}
