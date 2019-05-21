package com.izeye.sample.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import io.micrometer.core.instrument.logging.LoggingMeterRegistry;

/**
 * Configuration for Micrometer.
 *
 * @author Johnny Lim
 */
@Configuration
public class MicrometerConfig {

	@Bean
	public LoggingMeterRegistry loggingMeterRegistry() {
		return new LoggingMeterRegistry();
	}

}
