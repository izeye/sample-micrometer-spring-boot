package com.izeye.sample.web;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.izeye.sample.service.SampleService;

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
	private SampleService sampleService;

	@GetMapping("/call-rest-template")
	public Map<String, Object> callRestTemplate() {
		return this.restTemplate.getForObject("https://spring.io/info", Map.class);
	}

	@GetMapping("/do-something")
	public void doSomething() {
		this.sampleService.doSomething();
	}

}
