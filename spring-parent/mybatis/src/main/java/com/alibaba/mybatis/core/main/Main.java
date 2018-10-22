package com.alibaba.mybatis.core.main;

import com.alibaba.mybatis.core.dao.UserMapper;
import com.alibaba.mybatis.core.model.User;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.util.ObjectUtils;

/**
 * @author sier.pys 10/21/18
 */
public class Main {
    public static void main(String[] args) {
        ApplicationContext applicationContext = new ClassPathXmlApplicationContext("classpath*:application.xml");
        UserMapper userMapper = applicationContext.getBean("userMapper", UserMapper.class);
        User user = userMapper.findById();
        System.out.println(ObjectUtils.nullSafeToString(user));
    }
}
