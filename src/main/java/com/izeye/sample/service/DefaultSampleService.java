package com.izeye.sample.service;

import java.util.Map;
import java.util.concurrent.ThreadLocalRandom;

import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.Scheduled;
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

	private static final ParameterizedTypeReference<Map<String, Object>> MAP_STRING_OBJECT = new ParameterizedTypeReference<Map<String, Object>>() {
	};

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
				.minimumExpectedValue(10d)
				.maximumExpectedValue(10_000d)
				.serviceLevelObjectives(200d)
				.register(meterRegistry);
	}

	@Scheduled(fixedDelay = 1_000)
	public void recordDistributionSummary() {
		this.distributionSummary.record(ThreadLocalRandom.current().nextDouble() * 100);
	}

	@Override
	public Map<String, Object> doService() {
		Timer.Sample sample = Timer.start();
		try {
			ResponseEntity<Map<String, Object>> responseEntity = this.restTemplate.exchange(
					"https://spring.io/info", HttpMethod.GET, null, MAP_STRING_OBJECT);
			return responseEntity.getBody();
		}
		finally {
			sample.stop(this.timer);
		}
	}

	@Override
	public String doProxy(String url) {
		return this.restTemplate.getForObject(url, String.class);
	}

}
