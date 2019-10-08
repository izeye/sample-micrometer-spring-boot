package com.izeye.sample.config;

import java.util.Set;
import java.util.stream.Collectors;

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
			StatusAggregator statusAggregator, HealthContributorRegistry healthContributorRegistry,
			MeterRegistry meterRegistry) {
		HealthMetricsProvider healthMetricsProvider = new HealthMetricsProvider(
				healthContributorRegistry, statusAggregator);
		Gauge.builder("health", healthMetricsProvider, HealthMetricsProvider::getValue)
				.strongReference(true)
				.register(meterRegistry);
	}

	private static class HealthMetricsProvider {

		private final HealthContributorRegistry healthContributorRegistry;
		private final StatusAggregator statusAggregator;

		HealthMetricsProvider(HealthContributorRegistry healthContributorRegistry, StatusAggregator statusAggregator) {
			this.healthContributorRegistry = healthContributorRegistry;
			this.statusAggregator = statusAggregator;
		}

		private double getValue() {
			Set<Status> statuses = this.healthContributorRegistry.stream()
					.map(NamedContributor::getContributor)
					.filter(HealthIndicator.class::isInstance)
					.map(HealthIndicator.class::cast)
					.map((healthIndicator) -> healthIndicator.getHealth(false).getStatus())
					.collect(Collectors.toSet());
			Status aggregatedStatus = this.statusAggregator.getAggregateStatus(statuses);
			switch (aggregatedStatus.getCode()) {
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

}
