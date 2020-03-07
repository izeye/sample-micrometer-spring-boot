package micrometer.gh1882;

import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import io.micrometer.core.instrument.Counter;
import io.micrometer.core.instrument.MeterRegistry;
import io.micrometer.core.instrument.config.MeterRegistryConfig;
import io.micrometer.core.instrument.step.StepMeterRegistry;
import io.micrometer.core.instrument.step.StepRegistryConfig;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

public class MicrometerGh1882Tests {

	@Test
	public void counterStaysAtZero() {
		DebugApplication.main("", "--management.metrics.export.elastic.enabled=true", "--management.metrics.export.elastic.step=5s");
		// DebugApplication : Counter: 0.0 , [Measurement{statistic='COUNT', value=0.0}]
		// DebugApplication : Counter: 0.0 , [Measurement{statistic='COUNT', value=0.0}]
	}

	@Test
	public void counterAddsToOne() {
		DebugApplication.main("", "--management.metrics.export.elastic.enabled=false");
		// DebugApplication : Counter: 0.0 , [Measurement{statistic='COUNT', value=0.0}]
		// DebugApplication : Counter: 1.0 , [Measurement{statistic='COUNT', value=1.0}]
	}

	@SpringBootApplication
	@RequiredArgsConstructor
	@Slf4j
	public static class DebugApplication implements ApplicationRunner {

		public static void main(String... args) {
			log.info("{}", args[1]);
			SpringApplication.run(DebugApplication.class, args).close();
		}

		private final MeterRegistry registry;
		private final MeterRegistryConfig config;

		@Override
		public void run(ApplicationArguments args) throws Exception {
			log.info("MeterRegistry class: {}", registry.getClass());

			Counter counter = registry.counter("someCounter");
			log.info("Counter class: {}", counter.getClass());

			log.info("Counter: {} , {}", counter.count(), counter.measure());
			counter.increment();
			if (registry instanceof StepMeterRegistry) {
				Thread.sleep(((StepRegistryConfig) config).step().toMillis());
			}
			log.info("Counter: {} , {}", counter.count(), counter.measure());
		}

	}

}
