package com.alibaba.mbean;

import javax.management.MBeanServer;
import javax.management.MBeanServerFactory;
import javax.management.ObjectName;

/**
 * @author sier.pys 11/6/18
 */
public class App {
    public static void main(String[] args) throws Exception {
        MBeanServer mBeanServer =
                MBeanServerFactory.createMBeanServer();

        ObjectName objectName = new ObjectName("com.alibaba.mbean:type=Echo");

        Echo echo = new Echo();

        mBeanServer.registerMBean(echo, objectName);

        mBeanServer.invoke(objectName, "print", new Object[]{10}, new String[]{"java.lang.Integer"});
        System.in.read();
    }
}
