package com.izeye.sample.service;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import io.micrometer.core.instrument.MeterRegistry;
import io.micrometer.core.instrument.Timer;
import io.micrometer.influx.InfluxMeterRegistry;

/**
 * Default {@link SampleService}.
 *
 * @author Johnny Lim
 */
@Service
public class DefaultSampleService implements SampleService {

	@Autowired
	private MeterRegistry meterRegistry;

	@PostConstruct
	public void validate() {
		if (!(this.meterRegistry instanceof InfluxMeterRegistry)) {
			throw new IllegalStateException("Unexpected meter registry type: " + this.meterRegistry.getClass());
		}
	}

	@Override
	public String sayHello(String name) {
		Timer.Sample sample = Timer.start(this.meterRegistry);
		try {
			return "Hello, " + name + "!";
		}
		finally {
			Timer timer = this.meterRegistry.timer("hello.timer");
			sample.stop(timer);
		}
	}

}
