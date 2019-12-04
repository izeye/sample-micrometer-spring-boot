package com.izeye.sample.metrics;

import java.util.concurrent.TimeUnit;

import io.micrometer.core.instrument.Clock;
import io.micrometer.core.instrument.step.StepMeterRegistry;
import io.micrometer.core.instrument.util.NamedThreadFactory;

/**
 * Custom {@link StepMeterRegistry}.
 *
 * @author Johnny Lim
 */
public class CustomMeterRegistry extends StepMeterRegistry {

	public CustomMeterRegistry(CustomRegistryConfig config, Clock clock) {
		super(config, clock);

		start(new NamedThreadFactory("custom-metrics-publisher"));
	}

	@Override
	protected void publish() {
		getMeters().stream().forEach(meter -> System.out.println("Publishing " + meter.getId()));
	}

	@Override
	protected TimeUnit getBaseTimeUnit() {
		return TimeUnit.MILLISECONDS;
	}

}
