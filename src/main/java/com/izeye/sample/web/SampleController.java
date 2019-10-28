package com.izeye.sample.web;

import java.util.Map;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.izeye.sample.service.SampleService;

/**
 * Sample {@link RestController}.
 *
 * @author Johnny Lim
 */
@RestController
@RequestMapping(path = "/sample")
public class SampleController {

	private final SampleService service;

	public SampleController(SampleService service) {
		this.service = service;
	}

	@GetMapping("/doService")
	public Map<String, Object> doService() {
		return this.service.doService();
	}

}
