package com.izeye.sample.config;

import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Configuration for database.
 *
 * @author Johnny Lim
 */
@Configuration
public class DatabaseConfig {

	@Bean
	public org.apache.tomcat.jdbc.pool.DataSource dataSource() {
		return DataSourceBuilder.create()
				.type(org.apache.tomcat.jdbc.pool.DataSource.class)
				.url("jdbc:h2:mem:test;DB_CLOSE_DELAY=-1;DB_CLOSE_ON_EXIT=false").build();
	}

}
