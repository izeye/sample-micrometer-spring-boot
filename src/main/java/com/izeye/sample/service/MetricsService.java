package com.izeye.sample.service;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import io.micrometer.core.instrument.MeterRegistry;

/**
 * Service for metrics.
 *
 * @author Johnny Lim
 */
@Service
public class MetricsService {

	private final MeterRegistry meterRegistry;

	public MetricsService(MeterRegistry meterRegistry) {
		this.meterRegistry = meterRegistry;
	}

	@Scheduled(fixedRate = 1_000)
	public void printNumberOfMetrics() {
		System.out.println("# of metrics: " + this.meterRegistry.getMeters().size());
	}

}
