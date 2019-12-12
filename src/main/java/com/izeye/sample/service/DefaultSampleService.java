package com.izeye.sample.service;

import java.util.Map;
import java.util.concurrent.ThreadLocalRandom;

import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import io.micrometer.core.instrument.DistributionSummary;
import io.micrometer.core.instrument.MeterRegistry;
import io.micrometer.core.instrument.Timer;

/**
 * Default {@link SampleService}.
 *
 * @author Johnny Lim
 */
@Service
public class DefaultSampleService implements SampleService {

	private final RestTemplate restTemplate;

	private final Timer timer;

	private final DistributionSummary distributionSummary;

	public DefaultSampleService(RestTemplate restTemplate, MeterRegistry meterRegistry) {
		this.restTemplate = restTemplate;

		this.timer = Timer.builder("sample.time")
				.publishPercentiles(0.5, 0.75, 0.95, 0.99, 0.999)
				.publishPercentileHistogram()
				.register(meterRegistry);

		this.distributionSummary = DistributionSummary.builder("sample.value")
				.publishPercentiles(0.5, 0.75, 0.95, 0.99, 0.999)
				.publishPercentileHistogram()
				.register(meterRegistry);
	}

	@Override
	public Map<String, Object> doService() {
		this.distributionSummary.record(ThreadLocalRandom.current().nextDouble() * 100);

		Timer.Sample sample = Timer.start();
		try {
			return this.restTemplate.getForObject("https://spring.io/info", Map.class);
		}
		finally {
			sample.stop(this.timer);
		}
	}

}
