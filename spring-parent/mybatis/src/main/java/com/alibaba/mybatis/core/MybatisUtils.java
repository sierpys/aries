package com.alibaba.mybatis.core;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import java.io.IOException;
import java.io.InputStream;
import java.io.Reader;

/**
 * @author sier.pys 10/22/18
 */
public class MybatisUtils {
    private final static SqlSessionFactory sqlSessionFactory;

    static {
        String resource = "./mybatis-configuration.xml";
        Reader reader = null;
        try {
            reader = Resources.getResourceAsReader(resource);
        } catch (IOException e) {
            e.printStackTrace();
        }

        sqlSessionFactory = new SqlSessionFactoryBuilder().build(reader);

    }

    public static SqlSessionFactory sqlSessionFactory() {
        return sqlSessionFactory;
    }

}
