package com.izeye.sample.config;

import org.springframework.boot.web.embedded.jetty.ConfigurableJettyWebServerFactory;
import org.springframework.boot.web.server.WebServerFactoryCustomizer;
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
	public WebServerFactoryCustomizer<ConfigurableJettyWebServerFactory> jettyCustomizer() {
		return (jetty) -> {
			jetty.addServerCustomizers(this::setServer);
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
