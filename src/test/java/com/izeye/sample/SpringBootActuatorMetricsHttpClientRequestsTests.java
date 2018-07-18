package com.izeye.sample;

import java.util.Map;

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
 * {@literal http.client.requests} metrics tests.
 *
 * @author Johnny Lim
 */
@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class SpringBootActuatorMetricsHttpClientRequestsTests {

	@Autowired
	private TestRestTemplate restTemplate;

	@Test
	public void test() {
		ResponseEntity<String> responseEntity = this.restTemplate.exchange("/prometheus", HttpMethod.GET, null, String.class);
		assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.OK);
		assertThat(responseEntity.getBody()).doesNotContain("http_client_requests");

		Map<String, Object> response = this.restTemplate.getForObject("/sample/call-rest-template", Map.class);
		assertThat((Map<String, Object>) response.get("build")).containsEntry("artifact", "spring-io");

		responseEntity = this.restTemplate.exchange("/prometheus", HttpMethod.GET, null, String.class);
		assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.OK);
		assertThat(responseEntity.getBody()).contains("http_client_requests");
	}

}
