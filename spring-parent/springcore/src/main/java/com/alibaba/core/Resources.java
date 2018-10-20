package com.alibaba.core;

import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.core.io.support.ResourcePatternResolver;
import org.springframework.util.StreamUtils;

import java.io.IOException;
import java.nio.charset.Charset;

/**
 * @author sier.pys 10/19/18
 */
public class Resources {
    static final String DEFAULT_RESOURCE_PATTERN = "**/*.class";
    private static String resourcePattern = DEFAULT_RESOURCE_PATTERN;

    public static void main(String[] args) throws IOException {
        String packageSearchPath = ResourcePatternResolver.CLASSPATH_ALL_URL_PREFIX +
                "com/alibaba/core" + '/' + resourcePattern;
        ResourcePatternResolver resourcePatternResolver = new PathMatchingResourcePatternResolver();
        Resource[] resources = resourcePatternResolver.getResources(packageSearchPath);
        for (Resource resource : resources) {
            String copy = StreamUtils.copyToString(resource.getInputStream(), Charset.defaultCharset());
            System.out.println(copy);
        }
    }


//    public final Environment getEnvironment() {
//        if (this.environment == null) {
//            this.environment = new StandardEnvironment();
//        }
//        return this.environment;
//    }

//    private ResourcePatternResolver getResourcePatternResolver() {
//        if (this.resourcePatternResolver == null) {
//            this.resourcePatternResolver = new PathMatchingResourcePatternResolver();
//        }
//        return this.resourcePatternResolver;
//    }
}

