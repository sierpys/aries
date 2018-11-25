package com.alibaba.accessor;

import java.util.Properties;

/**
 * @author sier.pys 11/19/18
 */
public class MySQLAccessor extends Accessor implements ResourceAware {

    private static final String DRIVER = "com.mysql.jdbc.Driver";

    private Properties properties;


    public MySQLAccessor(String dbUrl, String username, String userPassword) {
        super(DRIVER, dbUrl, username, userPassword);
    }

    public void setProperties(Properties properties) {
        this.properties = properties;
    }
}
