package com.alibaba.accessor;

import java.util.Properties;

/**
 * @author sier.pys 11/19/18
 */
public class PostgreSQLAccessor extends Accessor {
    private Properties properties;
    private static final String DRIVER = "com.mysql.jdbc.Driver";

    public PostgreSQLAccessor(String dbUrl, String username, String userPassword) {
        super(DRIVER, dbUrl, username, userPassword);
    }

    public void setProperties(Properties properties) {
        this.properties = properties;
    }
}
