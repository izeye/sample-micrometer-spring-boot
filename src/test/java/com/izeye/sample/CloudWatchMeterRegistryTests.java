package com.izeye.sample;

import java.time.Duration;
import java.util.List;
import java.util.concurrent.TimeUnit;

import com.amazonaws.services.cloudwatch.AmazonCloudWatchAsync;
import com.amazonaws.services.cloudwatch.model.MetricDatum;
import com.amazonaws.services.cloudwatch.model.PutMetricDataRequest;
import io.micrometer.cloudwatch.CloudWatchConfig;
import io.micrometer.cloudwatch.CloudWatchMeterRegistry;
import io.micrometer.core.instrument.Clock;
import io.micrometer.core.instrument.Tags;
import org.junit.Test;
import org.mockito.ArgumentCaptor;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;

/**
 * Tests for {@link CloudWatchMeterRegistry}.
 *
 * @author Johnny Lim
 */
public class CloudWatchMeterRegistryTests {

	@Test
	public void test() throws InterruptedException {
		CloudWatchConfig config = new CloudWatchConfig() {

			@Override
			public String get(String key) {
				return null;
			}

			@Override
			public String namespace() {
				return "namespace";
			}

			@Override
			public Duration step() {
				return Duration.ofSeconds(5);
			}

		};

		AmazonCloudWatchAsync amazonCloudWatchAsync = mock(AmazonCloudWatchAsync.class);

		CloudWatchMeterRegistry registry = new CloudWatchMeterRegistry(config, Clock.SYSTEM, amazonCloudWatchAsync);
		registry.start();

		long value = 42L;

		registry.gauge("name", Tags.of("key", "value"), value);

		verify(amazonCloudWatchAsync, never()).putMetricDataAsync(any(), any());

		// NOTE: 5 seconds work in IntelliJ but don't work with Gradle.
		TimeUnit.SECONDS.sleep(6);

		ArgumentCaptor<PutMetricDataRequest> captor = ArgumentCaptor.forClass(PutMetricDataRequest.class);
		verify(amazonCloudWatchAsync).putMetricDataAsync(captor.capture(), any());

		PutMetricDataRequest request = captor.getValue();
		List<MetricDatum> metricData = request.getMetricData();
		assertThat(metricData).hasSize(1);
		assertThat(metricData.get(0).getValue()).isEqualTo(value);

		registry.stop();
	}

}
