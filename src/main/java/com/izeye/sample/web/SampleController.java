package com.izeye.sample.web;

import java.util.Map;
import java.util.concurrent.TimeUnit;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.async.DeferredResult;

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
