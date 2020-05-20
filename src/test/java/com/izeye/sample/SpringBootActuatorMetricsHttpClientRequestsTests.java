package com.izeye.sample;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

import org.junit.Test;
import org.junit.runner.RunWith;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * {@literal http.client.requests} metrics tests.
 *
 * @author Johnny Lim
 */
@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class SpringBootActuatorMetricsHttpClientRequestsTests {

	private static final ParameterizedTypeReference<Map<String, Object>> MAP_STRING_OBJECT = new ParameterizedTypeReference<Map<String, Object>>() {
	};

	@Autowired
	private TestRestTemplate restTemplate;

	@Test
	public void test() {
		ResponseEntity<Map<String, Object>> responseEntity = this.restTemplate.exchange(
				"/actuator/metrics/http.client.requests", HttpMethod.GET, null, MAP_STRING_OBJECT);
		assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.NOT_FOUND);

		responseEntity = this.restTemplate.exchange("/sample/doService", HttpMethod.GET, null, MAP_STRING_OBJECT);
		@SuppressWarnings("unchecked")
		Map<String, Object> build = (Map<String, Object>) responseEntity.getBody().get("build");
		assertThat(build).containsEntry("artifact", "spring-io");

		responseEntity = this.restTemplate.exchange(
				"/actuator/metrics/http.client.requests", HttpMethod.GET, null, MAP_STRING_OBJECT);
		assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.OK);
	}

}
