package com.izeye.sample.config;

import java.time.Duration;

import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

/**
 * Configuration for web.
 *
 * @author Johnny Lim
 */
@Configuration
public class WebConfig {

	@Bean
	public RestTemplate restTemplate(RestTemplateBuilder builder) {
		return builder.setConnectTimeout(Duration.ofSeconds(1)).setReadTimeout(Duration.ofSeconds(1)).build();
	}

}
