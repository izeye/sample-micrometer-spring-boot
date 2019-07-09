package com.izeye.sample.config;

import javax.sql.DataSource;

import org.h2.jdbcx.JdbcDataSource;

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
		JdbcDataSource dataSource = new JdbcDataSource();
		dataSource.setUrl("jdbc:h2:mem:mydb");
		return dataSource;
	}

}
