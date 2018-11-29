package com.izeye.sample.web;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
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

	private static final Logger log = LogManager.getLogger(SampleController.class);

	@Autowired
	private RestTemplate restTemplate;

	@GetMapping("/call-rest-template")
	public Map<String, Object> callRestTemplate() {
		log.info("Hello, world!");
		return this.restTemplate.getForObject("https://spring.io/info", Map.class);
	}

}
