package com.izeye.sample.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.izeye.sample.metrics.CustomMeterRegistry;
import com.izeye.sample.metrics.CustomRegistryConfig;
import io.micrometer.core.instrument.Clock;

/**
 * Configuration for metrics.
 *
 * @author Johnny Lim
 */
@Configuration
public class MetricsConfig {

	@Bean
	public CustomRegistryConfig customRegistryConfig() {
		return CustomRegistryConfig.DEFAULT;
	}

	@Bean
	public CustomMeterRegistry customMeterRegistry(CustomRegistryConfig customRegistryConfig, Clock clock) {
		return new CustomMeterRegistry(customRegistryConfig, clock);
	}

}
