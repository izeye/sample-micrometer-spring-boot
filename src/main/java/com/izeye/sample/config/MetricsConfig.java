package com.izeye.sample.config;

import java.time.Duration;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import io.micrometer.core.instrument.Clock;
import io.micrometer.dynatrace.DynatraceConfig;
import io.micrometer.dynatrace.DynatraceMeterRegistry;

/**
 * Configuration for metrics.
 *
 * @author Johnny Lim
 */
@Configuration
public class MetricsConfig {

	@Bean
	public DynatraceConfig dynatraceConfig(
			@Value("${dynatrace.api.key}") String apiToken,
			@Value("${dynatrace.environment.id}") String environmentId) {
		return new DynatraceConfig() {

			@Override
			public String get(String key) {
				return null;
			}

			@Override
			public String apiToken() {
				return apiToken;
			}

			@Override
			public String deviceId() {
				return "deviceId";
			}

			@Override
			public String uri() {
				return "https://" + environmentId + ".live.dynatrace.com";
			}

			@Override
			public Duration step() {
				return Duration.ofSeconds(10);
			}

		};
	}

	@Bean
	public DynatraceMeterRegistry dynatraceMeterRegistry(DynatraceConfig dynatraceConfig, Clock clock) {
		return DynatraceMeterRegistry.builder(dynatraceConfig).clock(clock).build();
	}

}
