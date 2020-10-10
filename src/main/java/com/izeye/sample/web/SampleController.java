package com.izeye.sample.web;

import java.util.Map;

import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
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
		ResponseEntity<Map<String, Object>> response = this.restTemplate.exchange(
				"https://spring.io/info",
				HttpMethod.GET,
				null,
				new ParameterizedTypeReference<Map<String, Object>>() {});
		return response.getBody();
	}

}
