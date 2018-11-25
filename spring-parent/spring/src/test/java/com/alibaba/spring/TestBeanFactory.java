package com.alibaba.spring;

import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.springframework.beans.factory.support.RootBeanDefinition;
import org.springframework.beans.factory.xml.XmlBeanDefinitionReader;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;

/**
 * @author sier.pys 11/3/18
 */
public class TestBeanFactory {
    private BeanFactory beanFactory;

    @Before
    public void setUp() {
        DefaultListableBeanFactory parent = new DefaultListableBeanFactory();
        parent.registerBeanDefinition("testBean", new RootBeanDefinition(TestBean.class));
        this.beanFactory = new DefaultListableBeanFactory(parent);
        ClassPathResource resource = new ClassPathResource("bean.xml");
        new XmlBeanDefinitionReader((BeanDefinitionRegistry) beanFactory).loadBeanDefinitions(resource);
    }

    @Test
    public void testGetBean() {
        TestBean testBean = beanFactory.getBean("testBean", TestBean.class);

        System.out.println(testBean.getCount());
    }
}
