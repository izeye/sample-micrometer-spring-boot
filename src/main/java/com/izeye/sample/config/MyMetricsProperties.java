package com.izeye.sample.config;

import java.util.LinkedHashMap;
import java.util.Map;

import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * Properties for metrics.
 *
 * @author Johnny Lim
 */
@ConfigurationProperties("my.management.metrics")
public class MyMetricsProperties {

	private final Map<String, String> tags = new LinkedHashMap<>();

	public Map<String, String> getTags() {
		return this.tags;
	}

}
