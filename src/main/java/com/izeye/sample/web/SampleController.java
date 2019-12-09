package com.izeye.sample.web;

import java.util.Map;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import io.micrometer.core.instrument.MeterRegistry;
import io.micrometer.core.instrument.Timer;

/**
 * Sample {@link RestController}.
 *
 * @author Johnny Lim
 */
@RestController
@RequestMapping(path = "/sample")
public class SampleController {

	private final RestTemplate restTemplate;

	private final Timer timer;

	public SampleController(RestTemplate restTemplate, MeterRegistry meterRegistry) {
		this.restTemplate = restTemplate;
		this.timer = meterRegistry.timer("my.timer");
	}

	@GetMapping("/call-rest-template")
	public Map<String, Object> callRestTemplate() {
		Timer.Sample sample = Timer.start();
		try {
			return this.restTemplate.getForObject("https://spring.io/info", Map.class);
		}
		finally {
			sample.stop(this.timer);
		}
	}

}
