package com.izeye.sample.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import io.micrometer.opentsdb.OpenTSDBConfig;
import io.micrometer.opentsdb.OpenTSDBMeterRegistry;

/**
 * Configuration for metrics.
 *
 * @author Johnny Lim
 */
@Configuration
public class MetricsConfig {

	@Bean
	public OpenTSDBMeterRegistry openTSDBMeterRegistry() {
		return OpenTSDBMeterRegistry.builder(OpenTSDBConfig.DEFAULT).build();
	}

}
