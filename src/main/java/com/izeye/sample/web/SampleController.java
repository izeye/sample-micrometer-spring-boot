package com.izeye.sample.web;

import java.util.Map;
import java.util.concurrent.TimeUnit;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.izeye.sample.service.SampleService;
import org.springframework.web.servlet.DispatcherServlet;

import javax.servlet.http.HttpServletRequest;

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

	@GetMapping("/doSleep")
	public String doSleep(@RequestParam int timeout, @RequestParam TimeUnit timeUnit) {
		try {
			timeUnit.sleep(timeout);
			return "Slept for " + timeout + " " + timeUnit.name().toLowerCase() + "!";
		} catch (InterruptedException ex) {
			Thread.currentThread().interrupt();
			throw new RuntimeException(ex);
		}
	}

	@GetMapping("/doProxy")
	public String doProxy(@RequestParam String url) {
		return this.service.doProxy(url);
	}

	@GetMapping("/setExceptionAttribute")
	public String set(HttpServletRequest request) {
		request.setAttribute(DispatcherServlet.EXCEPTION_ATTRIBUTE, new RuntimeException());
		return "setExceptionAttribute";
	}

}
