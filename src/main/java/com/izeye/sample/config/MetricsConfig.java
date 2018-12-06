package com.izeye.sample.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import io.micrometer.core.instrument.logging.LoggingMeterRegistry;

/**
 * Configuration for metrics.
 *
 * @author Johnny Lim
 */
@Configuration
public class MetricsConfig {

	@Bean
	public LoggingMeterRegistry loggingMeterRegistry() {
		return new LoggingMeterRegistry();
	}

}
