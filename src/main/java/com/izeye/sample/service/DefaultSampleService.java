package com.izeye.sample.service;

import java.util.Collections;

import javax.annotation.PostConstruct;

import io.micrometer.core.instrument.Measurement;
import io.micrometer.core.instrument.Meter;
import io.micrometer.core.instrument.Meter.Type;
import io.micrometer.core.instrument.MeterRegistry;
import io.micrometer.core.instrument.Statistic;

import org.springframework.stereotype.Service;

/**
 * Default {@link SampleService}.
 *
 * @author Johnny Lim
 */
@Service
public class DefaultSampleService implements SampleService {

	private final MeterRegistry meterRegistry;

	public DefaultSampleService(MeterRegistry meterRegistry) {
		this.meterRegistry = meterRegistry;
	}

	@PostConstruct
	public void init() {
		Measurement measurement = new Measurement(
				() -> Double.POSITIVE_INFINITY, Statistic.VALUE);
		Meter.builder("my.meter", Type.GAUGE,Collections.singletonList(measurement))
				.register(this.meterRegistry);
	}

	@Override
	public void doSomething() {
	}

}
