package com.izeye.sample.service;

import java.util.Map;
import java.util.concurrent.ThreadLocalRandom;

import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import io.micrometer.core.instrument.DistributionSummary;
import io.micrometer.core.instrument.MeterRegistry;

/**
 * Default {@link SampleService}.
 *
 * @author Johnny Lim
 */
@Service
public class DefaultSampleService implements SampleService {

	private final RestTemplate restTemplate;

	private final DistributionSummary distributionSummary;

	public DefaultSampleService(RestTemplate restTemplate, MeterRegistry meterRegistry) {
		this.restTemplate = restTemplate;

		this.distributionSummary = DistributionSummary.builder("sample.value")
				.publishPercentiles(0.5, 0.95, 0.99, 1)
				.register(meterRegistry);
	}

	@Override
	public Map<String, Object> doService() {
		this.distributionSummary.record(ThreadLocalRandom.current().nextDouble() * 100);

		return this.restTemplate.getForObject("https://spring.io/info", Map.class);
	}

}
