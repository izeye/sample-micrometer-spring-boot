package com.izeye.sample.web;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/")
public class MockDynatraceController {

	@PutMapping("/api/v1/timeseries/{timeseriesIdentifier}")
	public void putCustomMetric(@PathVariable String timeseriesIdentifier, @RequestBody String body) {
		System.out.println("PUT custom metric: " + timeseriesIdentifier);
		System.out.println(body);
	}

	@PostMapping("/api/v1/entity/infrastructure/custom/{customDeviceId}")
	public void postCustomDeviceMetric(@PathVariable String customDeviceId, @RequestBody String body) {
		System.out.println("POST custom device metric: " + customDeviceId);
		System.out.println(body);
	}

}
