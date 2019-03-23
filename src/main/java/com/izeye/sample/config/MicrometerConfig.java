package com.izeye.sample.config;

import java.time.Duration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import io.micrometer.core.instrument.Clock;
import io.micrometer.core.instrument.logging.LoggingMeterRegistry;
import io.micrometer.core.instrument.logging.LoggingRegistryConfig;

/**
 * Configuration for Micrometer.
 *
 * @author Johnny Lim
 */
@Configuration
public class MicrometerConfig {

	@Bean
	public LoggingRegistryConfig loggingRegistryConfig() {
		return new LoggingRegistryConfig() {

			@Override
			public String get(String s) {
				return null;
			}

			@Override
			public Duration step() {
				return Duration.ofSeconds(5);
			}

		};
	}

	@Bean
	public LoggingMeterRegistry loggingMeterRegistry(
			LoggingRegistryConfig loggingRegistryConfig, Clock clock) {
		return new LoggingMeterRegistry(loggingRegistryConfig, clock);
	}

}
