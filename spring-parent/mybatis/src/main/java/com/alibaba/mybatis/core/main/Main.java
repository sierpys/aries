package com.alibaba.mybatis.core.main;

import com.alibaba.mybatis.core.MybatisUtils;
import com.alibaba.mybatis.core.dao.UserMapper;
import com.alibaba.mybatis.core.model.User;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.util.ObjectUtils;

/**
 * @author sier.pys 10/21/18
 */
public class Main {
    static SqlSessionFactory sqlSessionFactory = MybatisUtils.sqlSessionFactory();


    public static void main(String[] args) {
//        ApplicationContext applicationContext = new ClassPathXmlApplicationContext("classpath*:application.xml");
//        UserMapper userMapper = applicationContext.getBean("userMapper", UserMapper.class);
//        User user = userMapper.findById();
//        System.out.println(ObjectUtils.nullSafeToString(user));
        SqlSession sqlSession = sqlSessionFactory.openSession();
        try {

            UserMapper mapper = sqlSession.getMapper(UserMapper.class);
            User user = mapper.findById();
            System.out.println(user);
//
//            Object o = sqlSession.selectOne("com.alibaba.mybatis.core.dao.UserMapper.findById");
//            System.out.println(o);
        } finally {
            sqlSession.close();
        }

    }
}
