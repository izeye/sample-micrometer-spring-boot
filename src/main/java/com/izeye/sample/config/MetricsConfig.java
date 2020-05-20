package com.izeye.sample.config;

import java.time.Duration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import io.micrometer.opentsdb.OpenTSDBConfig;
import io.micrometer.opentsdb.OpenTSDBMeterRegistry;

/**
 * Configuration for metrics.
 *
 * @author Johnny Lim
 */
@Configuration
public class MetricsConfig {

	@Bean
	public OpenTSDBConfig openTSDBConfig() {
		return new OpenTSDBConfig() {
			@Override
			public String get(String key) {
				return null;
			}

			@Override
			public Duration step() {
				return Duration.ofSeconds(5);
			}
		};
	}

	@Bean
	public OpenTSDBMeterRegistry openTSDBMeterRegistry(OpenTSDBConfig openTSDBConfig) {
		return OpenTSDBMeterRegistry.builder(openTSDBConfig).build();
	}

}
