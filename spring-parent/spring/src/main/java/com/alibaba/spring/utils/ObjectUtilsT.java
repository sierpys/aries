package com.alibaba.spring.utils;

import org.springframework.util.ObjectUtils;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.Properties;

/**
 * @author sier.pys 9/22/18
 */
public class ObjectUtilsT {
    public static void main(String[] args) {
        Map<String, Object> content = new HashMap<>();
        content.put("company", "alibaba");
        Properties properties = new Properties();
        properties.put("A", 1);
        properties.put("B", 2);
        content.put("inner", properties);
        System.out.println(ObjectUtils.nullSafeToString(content));

        Optional<HashMap<String, String>> optional = Optional.of(new HashMap<>());
        System.out.println(ObjectUtils.identityToString(ObjectUtils.unwrapOptional(optional)));
    }


}
