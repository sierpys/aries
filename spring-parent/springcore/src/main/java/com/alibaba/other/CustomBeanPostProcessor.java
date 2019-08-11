package com.alibaba.other;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;

/**
 * @author sier.pys 2019-06-22
 */
public class CustomBeanPostProcessor implements BeanPostProcessor {

    @Override
    public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
        Class clz = bean.getClass();
        Crawler crawler = (Crawler) clz.getAnnotation(Crawler.class);
        if (crawler != null) {
            String name = crawler.value();
            if (BaseCrawler.class.isAssignableFrom(clz)) {
                CrawlerCache.add(name, clz);
            }
        }
        return null;
    }
}
