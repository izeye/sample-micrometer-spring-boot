package com.izeye.sample.service;

import java.util.Map;
import java.util.concurrent.ThreadLocalRandom;

import com.izeye.sample.Logging;
import io.micrometer.observation.annotation.Observed;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
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
@Slf4j
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
				.register(meterRegistry);

		this.distributionSummary = DistributionSummary.builder("sample.value")
				.publishPercentiles(0.5, 0.75, 0.95, 0.99, 0.999)
				.register(meterRegistry);
	}

	@Logging
	@Observed
	@Override
	public Map<String, Object> doService() {
		log.info("Logging in DefaultSampleService...");

		this.distributionSummary.record(ThreadLocalRandom.current().nextDouble() * 100);

		Timer.Sample sample = Timer.start();
		try {
			ResponseEntity<Map<String, Object>> responseEntity = this.restTemplate.exchange(
					"https://spring.io/actuator/info", HttpMethod.GET, null, MAP_STRING_OBJECT);
			return responseEntity.getBody();
		}
		finally {
			sample.stop(this.timer);
		}
	}

}
