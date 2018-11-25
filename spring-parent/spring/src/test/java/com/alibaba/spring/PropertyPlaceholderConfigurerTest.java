package com.alibaba.spring;

import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.config.PropertyPlaceholderConfigurer;
import org.springframework.beans.factory.support.BeanDefinitionBuilder;
import org.springframework.beans.factory.support.BeanDefinitionReaderUtils;
import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;

/**
 * @author sier.pys 11/7/18
 */
public class PropertyPlaceholderConfigurerTest {
    @Test
    public void test() {
        DefaultListableBeanFactory beanFactory = new DefaultListableBeanFactory();
        beanFactory.registerBeanDefinition("testBean",
                BeanDefinitionBuilder.genericBeanDefinition(TestBean.class).addPropertyValue("name", "${name}").getBeanDefinition());

        PropertyPlaceholderConfigurer configurer = new PropertyPlaceholderConfigurer();
        Resource resource = new ClassPathResource("application.properties");
        configurer.setLocations(resource);

        configurer.postProcessBeanFactory(beanFactory);


        TestBean testBean = beanFactory.getBean("testBean", TestBean.class);
        Assert.assertEquals(testBean.getName(), "sier.pys");
    }

    @Test
    public void test_registerWithGeneratedName() {
        DefaultListableBeanFactory beanFactory = new DefaultListableBeanFactory();
//        beanFactory.registerBeanDefinition("testBean",
//                BeanDefinitionBuilder.genericBeanDefinition(TestBean.class).addPropertyValue("name", "${name}").getBeanDefinition());

        BeanDefinitionReaderUtils.registerWithGeneratedName(BeanDefinitionBuilder.genericBeanDefinition(TestBean.class).addPropertyValue("name", "${name}").getBeanDefinition(), beanFactory);


        PropertyPlaceholderConfigurer configurer = new PropertyPlaceholderConfigurer();
        Resource resource = new ClassPathResource("application.properties");
        configurer.setLocations(resource);

        configurer.postProcessBeanFactory(beanFactory);

        String[] namesForType = beanFactory.getBeanNamesForType(TestBean.class);


        TestBean testBean = beanFactory.getBean(TestBean.class);
        Assert.assertEquals(testBean.getName(), "sier.pys");
    }

}
