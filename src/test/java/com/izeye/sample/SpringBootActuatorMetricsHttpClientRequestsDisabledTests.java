package com.izeye.sample;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import io.micrometer.core.instrument.MeterRegistry;
import io.micrometer.core.instrument.search.MeterNotFoundException;
import org.junit.Test;
import org.junit.runner.RunWith;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

/**
 * {@literal http.client.requests} metrics disabled tests.
 *
 * @author Johnny Lim
 */
@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ActiveProfiles("http-client-requests-disabled")
public class SpringBootActuatorMetricsHttpClientRequestsDisabledTests {

	@Autowired
	private MeterRegistry meterRegistry;

	@Autowired
	private TestRestTemplate restTemplate;

	@Test
	public void test() {
		String name = "http.client.requests";

		assertThatThrownBy(() -> this.meterRegistry.get(name).timer())
				.isExactlyInstanceOf(MeterNotFoundException.class);

		Map<String, Object> response = this.restTemplate.getForObject("/sample/call-rest-template", Map.class);
		assertThat((Map<String, Object>) response.get("build")).containsEntry("artifact", "spring-io");

		assertThatThrownBy(() -> this.meterRegistry.get(name).timer())
				.isExactlyInstanceOf(MeterNotFoundException.class);
	}

}
