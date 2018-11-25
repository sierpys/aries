package com.alibaba.accessor;

import com.alibaba.accessor.support.ClassPathResource;
import com.alibaba.accessor.support.PropertiesLoaderSupport;
import com.alibaba.accessor.support.Resource;

import java.util.Properties;

/**
 * @author sier.pys 11/19/18
 */
public class AccessorFactory implements ResourceAware {
    private Resource resource;

    private final static String RESOUCE = "application.xml";

    public AccessorFactory(Resource resource) {
        this.resource = resource;
    }

    public AccessorFactory() {
        this.resource = new ClassPathResource(RESOUCE);
    }

    @Override
    public void setResource(Resource resource) {
        this.resource = resource;
    }


    Accessor mySQLAccessor() {
        Properties properties = new Properties();
        PropertiesLoaderSupport.loadProperties(properties, this.resource);
        String dbUrl = properties.getProperty("jdbcUrl");
        String user = properties.getProperty("user");
        String password = properties.getProperty("password");
        MySQLAccessor mySQLAccessor = new MySQLAccessor(dbUrl, user, password);
        mySQLAccessor.setProperties(properties);
        return mySQLAccessor;
    }


    Accessor postgreSQLAccessor() {
        Properties properties = new Properties();
        PropertiesLoaderSupport.loadProperties(properties, this.resource);
        String dbUrl = properties.getProperty("jdbcUrl");
        String user = properties.getProperty("user");
        String password = properties.getProperty("password");
        PostgreSQLAccessor postgreSQLAccessor = new PostgreSQLAccessor(dbUrl, user, password);
        postgreSQLAccessor.setProperties(properties);
        return postgreSQLAccessor;
    }

}
