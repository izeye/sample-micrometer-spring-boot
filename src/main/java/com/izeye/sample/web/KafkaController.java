package com.izeye.sample.web;

import java.time.Duration;
import java.util.Arrays;
import java.util.Properties;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicInteger;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import io.micrometer.core.instrument.MeterRegistry;
import io.micrometer.core.instrument.binder.kafka.KafkaClientMetrics;
import org.apache.kafka.clients.consumer.Consumer;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.apache.kafka.common.serialization.StringDeserializer;
import static org.apache.kafka.clients.consumer.ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG;
import static org.apache.kafka.clients.consumer.ConsumerConfig.CLIENT_ID_CONFIG;
import static org.apache.kafka.clients.consumer.ConsumerConfig.GROUP_ID_CONFIG;
import static org.apache.kafka.clients.consumer.ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG;
import static org.apache.kafka.clients.consumer.ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG;

/**
 * {@link RestController} for Kafka.
 *
 * @author Johnny Lim
 */
@RestController
@RequestMapping("/kafka")
public class KafkaController {

	private final AtomicInteger counter = new AtomicInteger(0);

	private final MeterRegistry meterRegistry;

	public KafkaController(MeterRegistry meterRegistry) {
		this.meterRegistry = meterRegistry;
	}

	@GetMapping("/consumers/new")
	public String createNewConsumer() {
		int id = counter.incrementAndGet();
		createNewConsumer(id);
		return "Creating a Kafka consumer #" + id + ".";
	}

	private void createNewConsumer(int id) {
		Executors.newSingleThreadExecutor().submit(() -> {
			try {
				Properties properties = new Properties();
				properties.setProperty(CLIENT_ID_CONFIG, "my-client-" + id);
				properties.setProperty(BOOTSTRAP_SERVERS_CONFIG, "localhost:9092");
				properties.setProperty(GROUP_ID_CONFIG, "my-group");
				properties.setProperty(KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class.getName());
				properties.setProperty(VALUE_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class.getName());

				Consumer<String, String> consumer = new KafkaConsumer<>(properties);
				consumer.subscribe(Arrays.asList("my-topic"));

				new KafkaClientMetrics(consumer).bindTo(meterRegistry);

				while (true) {
					ConsumerRecords<String, String> records = consumer.poll(Duration.ofMillis(100));
					for (ConsumerRecord<String, String> record : records) {
						System.out.printf("Offset = %d, key = %s, value = %s%n",
								record.offset(), record.key(), record.value());
					}
				}
			}
			catch (Throwable ex) {
				ex.printStackTrace();
			}
		});
	}

}
