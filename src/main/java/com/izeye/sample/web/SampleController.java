package com.izeye.sample.web;

import java.util.Map;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

/**
 * Sample {@link RestController}.
 *
 * @author Johnny Lim
 */
@RestController
@RequestMapping(path = "/sample")
public class SampleController {

	private final RestTemplate restTemplate;

	public SampleController(RestTemplate restTemplate) {
		this.restTemplate = restTemplate;
	}

	@GetMapping("/call-rest-template")
	public Map<String, Object> callRestTemplate() {
		return this.restTemplate.getForObject("https://spring.io/info", Map.class);
	}

}
