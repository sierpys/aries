package com.alibaba.aop.core;

import com.taobao.tair.DataEntry;
import com.taobao.tair.Result;
import com.taobao.tair.TairManager;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.TransactionCallback;
import org.springframework.transaction.support.TransactionTemplate;
import org.springframework.util.Assert;

/**
 * @author sier.pys 10/27/18
 */
public class XmlMain {

    public static void main(String[] args) {
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("application.xml");
        TairManager tairManager = context.getBean("tairManager", TairManager.class);
        tairManager.put(223, "AAAA", "BBBB");
        Result<DataEntry> aaaa = tairManager.get(223, "AAAA");
        if (aaaa != null) {
            System.out.println(aaaa.getValue());
        }
        assert tairManager != null;
    }
}
