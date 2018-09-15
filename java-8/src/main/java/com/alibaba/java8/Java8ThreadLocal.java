package com.alibaba.java8;

import java.util.HashMap;

/**
 * @author sier.pys 9/15/18
 */
public class Java8ThreadLocal {

    final static ThreadLocal<HashMap<String, Object>> context =
            ThreadLocal.<HashMap<String, Object>>withInitial(HashMap::new);
}
