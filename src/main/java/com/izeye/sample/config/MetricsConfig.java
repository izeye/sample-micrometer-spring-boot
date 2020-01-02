package com.izeye.sample.config;

import org.springframework.boot.actuate.autoconfigure.metrics.MeterRegistryCustomizer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import io.micrometer.core.instrument.Meter;
import io.micrometer.core.instrument.config.MeterFilter;
import io.micrometer.core.instrument.distribution.DistributionStatisticConfig;
import io.micrometer.prometheus.PrometheusMeterRegistry;

/**
 * Configuration for metrics.
 *
 * @author Johnny Lim
 */
@Configuration
public class MetricsConfig {

	@Bean
	public MeterRegistryCustomizer<PrometheusMeterRegistry> prometheusMeterRegistryCustomizer() {
		return (registry) -> registry.config().meterFilter(new MeterFilter() {

			@Override
			public DistributionStatisticConfig configure(Meter.Id id, DistributionStatisticConfig config) {
				if (id.getName().equals("sample.value")) {
					return DistributionStatisticConfig.builder()
							.minimumExpectedValue(10L)
							.maximumExpectedValue(10_000L)
							.build()
							.merge(config);
				}
				return config;
			}

		});
	}

}
