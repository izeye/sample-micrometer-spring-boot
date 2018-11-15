package com.izeye.sample.config;

import org.springframework.boot.actuate.autoconfigure.metrics.MeterRegistryCustomizer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import io.micrometer.core.instrument.MeterRegistry;

/**
 * Configuration for metrics.
 *
 * @author Johnny Lim
 */
@Configuration
public class MetricsConfig {

	@Bean
	public MeterRegistryCustomizer<MeterRegistry> commonTags() {
		return (meterRegistry) -> meterRegistry.config().commonTags("application", "my-application");
	}

}
