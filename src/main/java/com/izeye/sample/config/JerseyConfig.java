package com.izeye.sample.config;

import org.springframework.stereotype.Component;

import org.glassfish.jersey.server.ResourceConfig;

import com.izeye.sample.web.TestEndpoint;

/**
 * Configuration for Jersey.
 *
 * @author Johnny Lim
 */
@Component
public class JerseyConfig extends ResourceConfig {

	public JerseyConfig() {
		register(TestEndpoint.class);
	}

}
