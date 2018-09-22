package com.alibaba.core.spring.test.context;

import org.springframework.core.annotation.AnnotatedElementUtils;
import org.springframework.test.context.ContextConfigurationAttributes;
import org.springframework.test.context.ContextCustomizer;
import org.springframework.test.context.ContextCustomizerFactory;

import java.util.List;

/**
 * @author sier.pys 9/22/18
 */
public class EmbeddedKafkaContextCustomizerFactory implements ContextCustomizerFactory {
    @Override
    public ContextCustomizer createContextCustomizer(Class<?> testClass, List<ContextConfigurationAttributes> configAttributes) {
        EmbeddedKafka embeddedKafka = AnnotatedElementUtils.findMergedAnnotation(testClass, EmbeddedKafka.class);

        return embeddedKafka != null ? new EmbeddedKafkaContextCustomizers(embeddedKafka) : null;
    }
}
