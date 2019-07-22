package com.izeye.sample;

import java.util.Arrays;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.actuate.metrics.web.reactive.server.WebFluxTags;
import org.springframework.boot.actuate.metrics.web.reactive.server.WebFluxTagsProvider;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.web.server.ServerWebExchange;

import io.micrometer.core.instrument.Tag;

/**
 * Sample application.
 *
 * @author Johnny Lim
 */
@SpringBootApplication
public class Application {

	@Bean
	public RouteLocator myRoutes(RouteLocatorBuilder builder) {
		return builder.routes()
				.route(p -> p.path("/get")
						.filters(f -> f.addRequestHeader("Hello", "World"))
						.uri("http://httpbin.org:80/get"))
				.route(p -> p.path("/status/304")
						.uri("http://httpbin.org:80/status/304"))
				.build();
	}

	@Bean
	public WebFluxTagsProvider webFluxTagsProvider() {
		return (exchange, exception) -> Arrays.asList(WebFluxTags.method(exchange), getUri(exchange),
				WebFluxTags.exception(exception), WebFluxTags.status(exchange), WebFluxTags.outcome(exchange));
	}

	private Tag getUri(ServerWebExchange exchange) {
		return Tag.of("uri", exchange.getRequest().getURI().toString());
	}

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}

}
