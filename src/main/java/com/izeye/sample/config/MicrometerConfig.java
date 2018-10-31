package com.izeye.sample.config;

import javax.sql.DataSource;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import io.micrometer.core.instrument.binder.db.PostgreSQLDatabaseMetrics;

/**
 * Configuration for Micrometer.
 *
 * @author Johnny Lim
 */
@Configuration
public class MicrometerConfig {

	@Bean
	public PostgreSQLDatabaseMetrics postgreSQLDatabaseMetrics(DataSource dataSource) {
		return new PostgreSQLDatabaseMetrics(dataSource, "test");
	}

}
