package com.izeye.sample.web;

import java.util.Map;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.izeye.sample.service.SampleService;
import io.micrometer.core.annotation.Timed;

/**
 * Sample {@link RestController}.
 *
 * @author Johnny Lim
 */
@RestController
@RequestMapping(path = "/sample")
public class SampleController {

	private final SampleService sampleService;

	public SampleController(SampleService sampleService) {
		this.sampleService = sampleService;
	}

	@Timed("sample.controller")
	@GetMapping("/call-rest-template")
	public Map<String, Object> callRestTemplate() {
		return this.sampleService.callRestTemplate();
	}

}
