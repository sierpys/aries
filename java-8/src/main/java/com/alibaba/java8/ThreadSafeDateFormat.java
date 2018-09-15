package com.alibaba.java8;

import javax.swing.text.DateFormatter;

/**
 * @author sier.pys 9/15/18
 */
public class ThreadSafeDateFormat {
    final static ThreadLocal<DateFormatter> threadLocal = ThreadLocal.withInitial(DateFormatter::new);


    public DateFormatter formatter() {
        return threadLocal.get();
    }
}
