package com.izeye.sample.config;

import javax.sql.DataSource;

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
	public DataSource customDataSource() {
		return DataSourceBuilder.create().url("jdbc:h2:mem:mydb").build();
	}

}
