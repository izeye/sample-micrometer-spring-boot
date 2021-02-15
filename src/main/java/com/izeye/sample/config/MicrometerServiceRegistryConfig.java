package com.izeye.sample.config;

import io.micrometer.prometheus.PrometheusMeterRegistry;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.actuate.autoconfigure.metrics.MeterRegistryCustomizer;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

@Component
@ConditionalOnProperty(value = "micrometer.tracing", havingValue = "true")
public class MicrometerServiceRegistryConfig {

    @Value("${spring.application.name}")
    String appName;

    @Value("${spring.application.name}.${hostLocation}")
    String environment;

    @Value("${spring.application.name}.${originSystemId}")
    String originSystemId;

    @Bean
    MeterRegistryCustomizer<PrometheusMeterRegistry> metricsCommonTags() {
        return registry ->
                registry
                        .config()
                        .commonTags("appName", appName, "env", environment, "originSystemId", originSystemId);
    }

}
