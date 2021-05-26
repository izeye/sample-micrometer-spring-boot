package com.izeye.sample.config;

import io.micrometer.core.instrument.MeterRegistry;
import io.micrometer.core.instrument.binder.MeterBinder;
import org.springframework.boot.actuate.autoconfigure.metrics.MetricsProperties;
import org.springframework.boot.actuate.metrics.data.MetricsRepositoryMethodInvocationListener;
import org.springframework.boot.actuate.metrics.data.RepositoryTagsProvider;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Lazy;

/**
 * Configuration for metrics.
 *
 * @author Johnny Lim
 */
@Configuration
public class MetricsConfig {

    @Bean
    public MeterBinder myMeterBinder() {
        return (registry) -> registry.counter("binder.test");
    }

    // FIXME: See https://github.com/spring-projects/spring-boot/issues/26630
    @Bean
    public static MetricsRepositoryMethodInvocationListener metricsRepositoryMethodInvocationListener(
            MetricsProperties metricsProperties, @Lazy MeterRegistry registry, RepositoryTagsProvider tagsProvider) {
        MetricsProperties.Data.Repository properties = metricsProperties.getData().getRepository();
        return new MetricsRepositoryMethodInvocationListener(registry, tagsProvider, properties.getMetricName(),
                properties.getAutotime());
    }

}
