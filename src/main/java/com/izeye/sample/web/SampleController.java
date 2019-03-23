package com.izeye.sample.web;

import java.util.Map;
import java.util.concurrent.TimeUnit;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.context.request.async.DeferredResult;

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

	@GetMapping("/call-rest-template")
	public Map<String, Object> callRestTemplate() {
		return this.restTemplate.getForObject("https://spring.io/info", Map.class);
	}

	@GetMapping("/quotes")
	public DeferredResult<String> quotes() {
		DeferredResult<String> deferredResult = new DeferredResult<>();
		process(deferredResult);
		return deferredResult;
	}

	private void process(DeferredResult<String> deferredResult) {
		new Thread(() -> {
			try {
				TimeUnit.SECONDS.sleep(5);
				deferredResult.setResult("Hello, world!");
			} catch (InterruptedException ex) {
				Thread.currentThread().interrupt();
			}
		}).start();
	}

}
