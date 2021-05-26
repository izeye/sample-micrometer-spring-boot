package com.izeye.sample;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.actuate.metrics.AutoConfigureMetrics;
import org.springframework.boot.test.context.SpringBootTest;

/**
 * Tests for {@link Application}.
 *
 * @author Johnny Lim
 */
@SpringBootTest
// This is necessary to inject CollectorRegistry into the application when running an integration test.
@AutoConfigureMetrics
class ApplicationTests {

    @Test
    void contextLoads() {
    }

}
