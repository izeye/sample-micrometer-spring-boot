package com.izeye.sample;

import io.micrometer.core.instrument.DistributionSummary;
import io.micrometer.core.instrument.MeterRegistry;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Random;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

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
    public void run(ApplicationArguments args) throws Exception {
        Random random = new Random();

        List<String> paths = IntStream.range(1, 10).mapToObj((n) -> "path" + n).collect(Collectors.toList());
        List<String> mappingPaths = IntStream.range(1, 10).mapToObj((n) -> "mappingPath" + n).collect(Collectors.toList());
        List<String> serviceNames = IntStream.range(1, 10).mapToObj((n) -> "serviceName" + n).collect(Collectors.toList());
        List<String> statusCodes = IntStream.range(1, 10).mapToObj((n) -> "statusCode" + n).collect(Collectors.toList());

        while (true) {
            String path = paths.get(random.nextInt(paths.size()));
            String mappingPath = mappingPaths.get(random.nextInt(mappingPaths.size()));
            String serviceName = serviceNames.get(random.nextInt(serviceNames.size()));
            String statusCode = statusCodes.get(random.nextInt(statusCodes.size()));
            int cost = random.nextInt(100);

            DistributionSummary.builder("api.call.performance")
                    .tags("path", path,
                            "upstream.path", mappingPath,
                            "upstream.service", serviceName,
                            "status", statusCode)
                    .publishPercentileHistogram(false)
                    .serviceLevelObjectives(25, 50, 75, 90, 95)
                    .register(this.meterRegistry).record(cost);

            TimeUnit.MILLISECONDS.sleep(10);
        }

    }

}
