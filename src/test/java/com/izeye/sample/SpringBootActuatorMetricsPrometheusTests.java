package com.izeye.sample;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

import org.junit.Test;
import org.junit.runner.RunWith;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * Tests for Spring Boot Actuator Prometheus scrape endpoint.
 *
 * @author Johnny Lim
 */
@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class SpringBootActuatorMetricsPrometheusTests {

	@Autowired
	private TestRestTemplate restTemplate;

	@Test
	public void test() {
		ResponseEntity<String> responseEntity = this.restTemplate.exchange("/prometheus", HttpMethod.GET, null, String.class);
		assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.OK);
		assertThat(responseEntity.getBody()).contains("strings_count 2.0");

		String response = this.restTemplate.getForObject("/sample/put?key=Test3&value=Test", String.class);
		assertThat(response).isNull();

		responseEntity = this.restTemplate.exchange("/prometheus", HttpMethod.GET, null, String.class);
		assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.OK);
		assertThat(responseEntity.getBody()).contains("strings_count 3.0");
	}

}
