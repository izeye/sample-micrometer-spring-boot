package com.izeye.sample.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import io.micrometer.core.instrument.Counter;
import io.micrometer.core.instrument.MeterRegistry;

/**
 * Configuration for metrics.
 *
 * @author Johnny Lim
 */
@Configuration
public class MetricsConfig {

	@Bean
	public Counter myCounter(MeterRegistry meterRegistry) {
		return Counter.builder("myCounter").register(meterRegistry);
	}

}
