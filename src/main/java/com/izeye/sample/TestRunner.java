package com.izeye.sample;

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
        for (int i = 0; i < 10; i++) {
            ScheduledThreadPoolExecutor scheduledThreadPoolExecutor = new ScheduledThreadPoolExecutor(10);
            new ExecutorServiceMetrics(scheduledThreadPoolExecutor, "my.executor.service." + i, Tags.empty())
                    .bindTo(this.meterRegistry);
        }
    }

}
