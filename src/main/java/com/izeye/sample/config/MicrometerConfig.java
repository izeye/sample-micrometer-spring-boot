package com.izeye.sample.config;

import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import io.micrometer.spring.web.servlet.WebMvcMetricsFilter;

/**
 * Configuration for Micrometer.
 *
 * @author Johnny Lim
 */
@Configuration
public class MicrometerConfig {

	@Bean
	public FilterRegistrationBean webMvcMetricsFilterDisable(WebMvcMetricsFilter webMvcMetricsFilter) {
		FilterRegistrationBean filterRegistrationBean = new FilterRegistrationBean(webMvcMetricsFilter);
		filterRegistrationBean.setEnabled(false);
		return filterRegistrationBean;
	}

}
