package com.alibaba.spring;

import com.alibaba.spring.config.Config;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.AnnotationConfigUtils;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.context.support.GenericApplicationContext;
import org.springframework.util.Assert;

/**
 * @author sier.pys 9/15/18
 */
public class ContextLoader {
    private static ApplicationContext context;

    private Thread shutdownHook;

    public synchronized static ApplicationContext getContext() {
        if (context == null) {
            context = new ClassPathXmlApplicationContext("classpath*:*.xml");
        }
        return context;
    }

    public static <T> T getBean(String name, Class<T> claz) {
        Assert.notNull(name, "name must not be null");
        Assert.notNull(claz, "Class type must not be null");
        return getContext().getBean(name, claz);
    }

    public static void main(String[] args) {
        GenericApplicationContext ctx = new GenericApplicationContext(getContext());
        AnnotationConfigUtils.registerAnnotationConfigProcessors(ctx);

        ctx.refresh();
        ctx.registerShutdownHook();

        S2 s2 = ctx.getBean("s2", S2.class);
        System.out.println(s2.find());
//        ApplicationContext applicationContext = new AnnotationConfigApplicationContext(Config.class);
//        S2 s2 = applicationContext.getBean("s2", S2.class);
//        System.out.println(s2.find());

    }

    private void doClose() {
        System.out.println("close");
    }

    void regiterShutdownHook() {
        if (shutdownHook == null) {
            this.shutdownHook = new Thread(this::doClose);
        }

        Runtime.getRuntime().addShutdownHook(this.shutdownHook);
    }
}
