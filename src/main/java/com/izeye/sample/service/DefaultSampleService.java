package com.izeye.sample.service;

import java.util.Map;
import java.util.concurrent.ThreadLocalRandom;

import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import io.micrometer.core.instrument.DistributionSummary;
import io.micrometer.core.instrument.LongTaskTimer;
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

	private final LongTaskTimer longTaskTimer;

	public DefaultSampleService(RestTemplate restTemplate, MeterRegistry meterRegistry) {
		this.restTemplate = restTemplate;

		this.timer = Timer.builder("sample.time")
				.publishPercentiles(0.5, 0.75, 0.95, 0.99, 0.999)
				.register(meterRegistry);

		this.distributionSummary = DistributionSummary.builder("sample.value")
				.publishPercentiles(0.5, 0.75, 0.95, 0.99, 0.999)
				.register(meterRegistry);

		this.longTaskTimer = LongTaskTimer.builder("sample.long.task.timer")
				.register(meterRegistry);
	}

	@Override
	public Map<String, Object> doService() {
		this.distributionSummary.record(ThreadLocalRandom.current().nextDouble() * 100);

		Timer.Sample sample = Timer.start();

		LongTaskTimer.Sample longTaskTimerSample = this.longTaskTimer.start();
		try {
			ResponseEntity<Map<String, Object>> responseEntity = this.restTemplate.exchange(
					"https://spring.io/info", HttpMethod.GET, null, MAP_STRING_OBJECT);
			return responseEntity.getBody();
		}
		finally {
			sample.stop(this.timer);

			longTaskTimerSample.stop();
		}
	}

}
