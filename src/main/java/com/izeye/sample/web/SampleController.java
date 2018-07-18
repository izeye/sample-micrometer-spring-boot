package com.izeye.sample.web;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import io.micrometer.core.instrument.Tags;
import io.micrometer.prometheus.PrometheusMeterRegistry;

/**
 * Sample {@link RestController}.
 *
 * @author Johnny Lim
 */
@RestController
@RequestMapping(path = "/sample")
public class SampleController {

	@Autowired
	private PrometheusMeterRegistry registry;

	private final Map<String, String> _strings = new ConcurrentHashMap<>();

	@PostConstruct
	public void init() {
		this._strings.put("Test", "Test");
		this._strings.put("Test2", "Test");

		this.registry.gaugeCollectionSize("strings_count", Tags.empty(), this._strings.keySet());
	}

	@GetMapping("/put")
	public String put(@RequestParam String key, @RequestParam String value) {
		return this._strings.put(key, value);
	}

}
