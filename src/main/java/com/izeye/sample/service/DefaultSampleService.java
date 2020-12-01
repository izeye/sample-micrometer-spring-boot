package com.izeye.sample.service;

import java.util.Map;

import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import io.micrometer.core.annotation.Timed;

/**
 * Default {@link SampleService}.
 *
 * @author Johnny Lim
 */
@Service
public class DefaultSampleService implements SampleService {

	private final RestTemplate restTemplate;

	public DefaultSampleService(RestTemplate restTemplate) {
		this.restTemplate = restTemplate;
	}

	@Timed("sample.service")
	@Override
	public Map<String, Object> callRestTemplate() {
		return this.restTemplate.exchange(
				"https://spring.io/actuator/info",
				HttpMethod.GET,
				null,
				new ParameterizedTypeReference<Map<String, Object>>() {}).getBody();
	}

}
