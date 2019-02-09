package com.izeye.sample.service;

import java.util.concurrent.TimeUnit;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import io.micrometer.core.instrument.MeterRegistry;
import io.micrometer.core.instrument.Tags;

/**
 * Default {@link SampleService}.
 *
 * @author Johnnh Lim
 */
@Service
public class DefaultSampleService implements SampleService {

	@Autowired
	private MeterRegistry meterRegistry;

	private final Object object = new Object();

	@PostConstruct
	public void init() {
		this.meterRegistry.more().timer(
				"my.function.timer.nanoseconds", Tags.empty(), this.object, (o) -> 1L, (o) -> 1d, TimeUnit.NANOSECONDS);
		this.meterRegistry.more().timer(
				"my.function.timer.milliseconds", Tags.empty(), this.object, (o) -> 1L, (o) -> 1d, TimeUnit.MILLISECONDS);
	}

	@Override
	public void doSomething() {
		System.out.println("Doing something...");
	}

}
