package com.izeye.sample.config;

import io.micrometer.core.instrument.MeterRegistry;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * Tests for {@link MetricsConfig}.
 *
 * @author Johnny Lim
 */
@SpringBootTest
class MetricsConfigTests {

    @Autowired
    private MeterRegistry registry;

    @Test
    void userDefinedMeterBinderWorks() {
        assertThat(this.registry.find("binder.test").counter()).isNotNull();
    }

}
