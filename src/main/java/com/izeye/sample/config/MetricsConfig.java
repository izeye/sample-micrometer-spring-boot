package com.izeye.sample.config;

import java.time.Duration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import io.micrometer.core.instrument.Clock;
import io.micrometer.core.instrument.Meter;
import io.micrometer.core.instrument.config.MeterFilter;
import io.micrometer.core.instrument.distribution.DistributionStatisticConfig;
import io.micrometer.core.instrument.logging.LoggingMeterRegistry;
import io.micrometer.core.instrument.logging.LoggingRegistryConfig;

/**
 * Configuration for metrics.
 *
 * @author Johnny Lim
 */
@Configuration
public class MetricsConfig {

	@Bean
	public LoggingMeterRegistry loggingMeterRegistry() {
		LoggingMeterRegistry loggingMeterRegistry = new LoggingMeterRegistry(new LoggingRegistryConfig() {

			@Override
			public String get(String key) {
				return null;
			}

			@Override
			public Duration step() {
				return Duration.ofSeconds(5);
			}

		}, Clock.SYSTEM);

		loggingMeterRegistry.config().meterFilter(new MeterFilter() {
			@Override
			public DistributionStatisticConfig configure(Meter.Id id, DistributionStatisticConfig config) {
				return DistributionStatisticConfig.builder().expiry(Duration.ofHours(1)).build().merge(config);
			}
		});
		return loggingMeterRegistry;
	}

}
