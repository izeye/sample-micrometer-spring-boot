package com.izeye.sample.config;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import io.micrometer.core.instrument.Tag;
import io.micrometer.core.instrument.config.MeterFilter;

/**
 * Configuration for metrics.
 *
 * @author Johnny Lim
 */
@Configuration
@EnableConfigurationProperties(MyMetricsProperties.class)
public class MetricsConfig {

	@Bean
	public MeterFilter commonTagsMeterFilter(MyMetricsProperties properties) {
		return MeterFilter.commonTags(createCommonTags(properties.getTags()));
	}

	private List<Tag> createCommonTags(Map<String, String> tags) {
		return tags.entrySet().stream()
				.map((entry) -> Tag.of(entry.getKey(), entry.getValue()))
				.collect(Collectors.toList());
	}

}
