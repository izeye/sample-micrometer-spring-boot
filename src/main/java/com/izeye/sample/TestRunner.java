package com.izeye.sample;

import io.micrometer.core.instrument.Meter;
import io.micrometer.core.instrument.MeterRegistry;
import io.micrometer.core.instrument.Tags;
import io.micrometer.core.instrument.binder.jvm.ExecutorServiceMetrics;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import java.util.concurrent.ScheduledThreadPoolExecutor;

/**
 * {@link ApplicationRunner} for testing.
 *
 * @author Johnny Lim
 */
@Component
public class TestRunner implements ApplicationRunner {

    private final MeterRegistry meterRegistry;

    public TestRunner(MeterRegistry meterRegistry) {
        this.meterRegistry = meterRegistry;
    }

    @Override
    public void run(ApplicationArguments args) {
        createExecutor("another.executor.service", Tags.of("key1", "value1"));

        for (int i = 0; i < 10; i++) {
            String executorServiceName = "my.executor.service." + i;
            Tags tags = Tags.empty();

            createExecutor(executorServiceName, tags);
        }

        for (Meter meter : this.meterRegistry.getMeters()) {
            Meter.Id id = meter.getId();
            if (id.getName().startsWith("executor.")) {
                System.out.println(id);
            }
        }
    }

    private void createExecutor(String executorServiceName, Tags tags) {
        ScheduledThreadPoolExecutor scheduledThreadPoolExecutor = new ScheduledThreadPoolExecutor(10);
        new ExecutorServiceMetrics(scheduledThreadPoolExecutor, executorServiceName, tags)
                .bindTo(this.meterRegistry);
    }

}
