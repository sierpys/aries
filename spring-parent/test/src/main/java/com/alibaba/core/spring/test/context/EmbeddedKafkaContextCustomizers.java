package com.alibaba.core.spring.test.context;

import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.test.context.ContextCustomizer;
import org.springframework.test.context.MergedContextConfiguration;

/**
 * @author sier.pys 9/22/18
 */
public class EmbeddedKafkaContextCustomizers implements ContextCustomizer {
    private final EmbeddedKafka embeddedKafka;

    public EmbeddedKafkaContextCustomizers(EmbeddedKafka embeddedKafka) {
        this.embeddedKafka = embeddedKafka;
    }

    @Override
    public void customizeContext(ConfigurableApplicationContext context, MergedContextConfiguration mergedConfig) {

    }
}
