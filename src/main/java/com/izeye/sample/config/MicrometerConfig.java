package com.izeye.sample.config;

import org.springframework.beans.factory.annotation.Value;
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
	public StackdriverConfig stackdriverConfig(@Value("${stackdriver.project-id}") String projectId) {
		return new StackdriverConfig() {

			@Override
			public String get(String s) {
				return null;
			}

			@Override
			public String projectId() {
				return projectId;
			}

		};
	}

	@Bean
	public StackdriverMeterRegistry stackdriverMeterRegistry(StackdriverConfig stackdriverConfig) {
		return StackdriverMeterRegistry.builder(stackdriverConfig).build();
	}

}
