package com.izeye.sample.service;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
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

	@Autowired
	private RestTemplate restTemplate;

	@Timed("sample.service")
	@Override
	public Map<String, Object> callRestTemplate() {
		return this.restTemplate.getForObject("https://spring.io/actuator/info", Map.class);
	}

}
