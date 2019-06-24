package com.izeye.sample.web;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import io.micrometer.core.instrument.Counter;

/**
 * Sample {@link RestController}.
 *
 * @author Johnny Lim
 */
@RestController
@RequestMapping(path = "/sample")
public class SampleController {

	@Autowired
	private RestTemplate restTemplate;

	@Autowired
	private Counter myCounter;

	@GetMapping("/call-rest-template")
	public Map<String, Object> callRestTemplate() {
		this.myCounter.increment();
		return this.restTemplate.getForObject("https://spring.io/info", Map.class);
	}

}
