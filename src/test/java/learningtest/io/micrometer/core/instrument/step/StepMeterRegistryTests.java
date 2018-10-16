package learningtest.io.micrometer.core.instrument.step;

import java.time.Duration;
import java.util.Collection;
import java.util.concurrent.TimeUnit;

import io.micrometer.core.instrument.Clock;
import io.micrometer.core.instrument.Counter;
import io.micrometer.core.instrument.step.StepMeterRegistry;
import io.micrometer.core.instrument.step.StepRegistryConfig;
import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * Tests for {@link StepMeterRegistry}.
 *
 * @author Johnny Lim
 */
public class StepMeterRegistryTests {

	private final StepRegistryConfig config = new StepRegistryConfig() {

		@Override
		public String prefix() {
			return null;
		}

		@Override
		public String get(String key) {
			return null;
		}

		@Override
		public Duration step() {
			return Duration.ofSeconds(5);
		}
	};

	private final StepMeterRegistry registry = new StepMeterRegistry(this.config, Clock.SYSTEM) {

		@Override
		protected void publish() {
		}

		@Override
		protected TimeUnit getBaseTimeUnit() {
			return TimeUnit.MILLISECONDS;
		}

	};

	@Test
	public void find() throws InterruptedException {
		Counter myCounter1 = this.registry.counter("myTestCounter", "tag1", "value1");
		Counter myCounter2 = this.registry.counter("myTestCounter", "tag2", "value2");
		Counter myCounter1n2 = this.registry.counter("myTestCounter", "tag1", "value1", "tag2", "value2");

		myCounter1.increment();
		myCounter2.increment();
		myCounter1n2.increment();

		TimeUnit.SECONDS.sleep(5);

		assertThat(myCounter1.count()).isEqualTo(1);
		assertThat(myCounter2.count()).isEqualTo(1);
		assertThat(myCounter1n2.count()).isEqualTo(1);

		Collection<Counter> counters = this.registry.find("myTestCounter").tag("tag1", "value1").counters();
		assertThat(counters).containsExactlyInAnyOrder(myCounter1, myCounter1n2);
		assertThat(aggregate(counters)).isEqualTo(2);

		counters = this.registry.find("myTestCounter").tag("tag2", "value2").counters();
		assertThat(counters).containsExactlyInAnyOrder(myCounter2, myCounter1n2);
		assertThat(aggregate(counters)).isEqualTo(2);
	}

	private double aggregate(Collection<Counter> counters) {
		return counters.stream().mapToDouble(Counter::count).sum();
	}

}
