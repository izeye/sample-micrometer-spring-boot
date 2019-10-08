package com.izeye.sample.config;

import org.springframework.boot.actuate.health.*;
import org.springframework.context.annotation.Configuration;

import io.micrometer.core.instrument.Gauge;
import io.micrometer.core.instrument.MeterRegistry;

/**
 * Configuration for health metrics.
 *
 * @author Johnny Lim
 */
@Configuration
public class HealthMetricsConfig {

	public HealthMetricsConfig(
			HealthAggregator healthAggregator, HealthIndicatorRegistry healthIndicatorRegistry,
			MeterRegistry meterRegistry) {
		CompositeHealthIndicator healthIndicator = new CompositeHealthIndicator(healthAggregator, healthIndicatorRegistry);
		Gauge.builder("health", healthIndicator, this::getValue)
				.strongReference(true)
				.register(meterRegistry);
	}

	private double getValue(CompositeHealthIndicator healthIndicator) {
		switch (healthIndicator.health().getStatus().getCode()) {
			case "UP":
				return 3;
			case "OUT_OF_SERVICE":
				return 2;
			case "DOWN":
				return 1;
			case "UNKNOWN":
			default:
				return 0;
		}
	}

}
