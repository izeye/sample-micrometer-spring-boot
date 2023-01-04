package com.izeye.sample;

import com.izeye.sample.service.SampleService;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

/**
 * {@link ApplicationRunner} for testing.
 *
 * @author Johnny Lim
 */
@Component
public class TestApplicationRunner implements ApplicationRunner {

    private final SampleService sampleService;

    public TestApplicationRunner(SampleService sampleService) {
        this.sampleService = sampleService;
    }

    @Override
    public void run(ApplicationArguments args) {
        this.sampleService.doService();
    }

}
