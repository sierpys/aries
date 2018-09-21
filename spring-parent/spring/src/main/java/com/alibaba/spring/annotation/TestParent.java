package com.alibaba.spring.annotation;

import javax.servlet.http.HttpServletRequest;

/**
 * @author sier.pys 9/21/18
 */
@ClassNameAnnotation(className = "parent")
public class TestParent {
    @Schedule(dayOfMonth = "11")
    @Schedule(dayOfWeek = "22")
    @StaticTextAnnotation(value = "Custom text value", text = "Test Text")
    public String test(HttpServletRequest request) {
        return "test";
    }

    @ClassNameAnnotation(className = "child")
    public static class TestChildren extends TestParent {
        @Override
        @Schedule(dayOfMonth = "3333")
        @Schedule(dayOfWeek = "4444")
        public String test(HttpServletRequest request) {
            return super.test(request);
        }
    }
}

