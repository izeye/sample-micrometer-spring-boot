package com.izeye.sample.service;

import java.util.concurrent.TimeUnit;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import io.micrometer.core.instrument.Counter;
import io.micrometer.core.instrument.MeterRegistry;

/**
 * Initializer for metrics.
 *
 * @author Johnny Lim
 */
@Service
public class MetricsInitializer {

	@Autowired
	private MeterRegistry meterRegistry;

	@PostConstruct
	public void init() {
		Counter counter = this.meterRegistry.counter("my.counter", "k", "v", "k2", "v2", "k3", "v3", "k4", "v4");

		new Thread(() -> {
			while (true) {
				counter.increment();
				try {
					TimeUnit.SECONDS.sleep(1);
				}
				catch (InterruptedException ex) {
					throw new RuntimeException(ex);
				}
			}
		}).start();
	}

}
