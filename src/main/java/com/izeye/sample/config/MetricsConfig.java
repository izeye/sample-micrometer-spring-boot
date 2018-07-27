package com.izeye.sample.config;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.stereotype.Component;

import io.micrometer.core.instrument.MeterRegistry;
import io.micrometer.core.instrument.Tag;

/**
 * Configuration for metrics.
 *
 * @author Johnny Lim
 */
@Component
@EnableConfigurationProperties(MyMetricsProperties.class)
public class MetricsConfig {

	@Autowired
	private MyMetricsProperties properties;

	@Autowired
	private MeterRegistry meterRegistry;

	@PostConstruct
	public void initialize() {
		this.meterRegistry.config().commonTags(createCommonTags(this.properties.getTags()));
	}

	private List<Tag> createCommonTags(Map<String, String> tags) {
		return tags.entrySet().stream()
				.map((entry) -> Tag.of(entry.getKey(), entry.getValue()))
				.collect(Collectors.toList());
	}

}
