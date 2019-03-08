package com.izeye.sample.config;

import java.time.Duration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import io.micrometer.stackdriver.StackdriverConfig;
import io.micrometer.stackdriver.StackdriverMeterRegistry;

/**
 * Configuration for Micrometer.
 *
 * @author Johnny Lim
 */
@Configuration
public class MicrometerConfig {

	@Bean
	public StackdriverConfig stackdriverConfig() {
		return new StackdriverConfig() {

			@Override
			public String get(String s) {
				return null;
			}

			@Override
			public String projectId() {
				return System.getProperty("projectId");
			}

			@Override
			public Duration step() {
				return Duration.ofSeconds(5);
			}

		};
	}

	@Bean
	public StackdriverMeterRegistry stackdriverMeterRegistry(StackdriverConfig stackdriverConfig) {
		return StackdriverMeterRegistry.builder(stackdriverConfig).build();
	}

}
