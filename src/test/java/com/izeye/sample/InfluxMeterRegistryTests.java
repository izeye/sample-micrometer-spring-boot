package com.izeye.sample;

import java.time.Duration;
import java.util.concurrent.TimeUnit;

import io.micrometer.core.instrument.Clock;
import io.micrometer.core.instrument.Counter;
import io.micrometer.core.instrument.Tags;
import io.micrometer.core.instrument.step.StepCounter;
import io.micrometer.influx.InfluxConfig;
import io.micrometer.influx.InfluxMeterRegistry;
import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * Tests for {@link InfluxMeterRegistry}.
 *
 * @author Johnny Lim
 */
public class InfluxMeterRegistryTests {

	@Test
	public void test() throws InterruptedException {
		InfluxConfig config = new InfluxConfig() {

			@Override
			public String get(String key) {
				return null;
			}

			@Override
			public Duration step() {
				return Duration.ofSeconds(5);
			}

		};

		InfluxMeterRegistry registry = new InfluxMeterRegistry(config, Clock.SYSTEM);

		Counter counter = registry.counter("name", Tags.of("key", "value"));
		assertThat(counter).isExactlyInstanceOf(StepCounter.class);
		counter.increment();
		assertThat(counter.count()).isEqualTo(0);

		TimeUnit.SECONDS.sleep(5);

		assertThat(counter.count()).isEqualTo(1);

		TimeUnit.SECONDS.sleep(5);

		assertThat(counter.count()).isEqualTo(0);
	}

}
