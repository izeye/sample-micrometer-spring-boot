package com.izeye.sample.config;

import java.util.Collections;

import org.springframework.boot.context.embedded.EmbeddedServletContainerCustomizer;
import org.springframework.boot.context.embedded.jetty.JettyEmbeddedServletContainerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.util.thread.ThreadPool;

/**
 * Configuration for Jetty.
 *
 * @author Johnny Lim
 */
@Configuration
public class JettyConfig {

	private volatile Server server;

	@Bean
	public EmbeddedServletContainerCustomizer jettyCustomizer() {
		return (jetty) -> {
			((JettyEmbeddedServletContainerFactory) jetty).setServerCustomizers(Collections.singleton(this::setServer));
		};
	}

	@Bean
	public ThreadPool jettyThreadPool() {
		return this.server.getThreadPool();
	}

	private void setServer(Server server) {
		this.server = server;
	}

}
