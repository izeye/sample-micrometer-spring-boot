package com.izeye.sample.service;

import java.time.Duration;
import java.util.Arrays;
import java.util.Properties;
import java.util.concurrent.Executors;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import org.apache.kafka.clients.consumer.Consumer;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.apache.kafka.common.serialization.StringDeserializer;

import static org.apache.kafka.clients.consumer.ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG;
import static org.apache.kafka.clients.consumer.ConsumerConfig.GROUP_ID_CONFIG;
import static org.apache.kafka.clients.consumer.ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG;
import static org.apache.kafka.clients.consumer.ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG;

/**
 * Service for Kafka.
 *
 * @author Johnny Lim
 */
@Service
public class KafkaService {

	@Scheduled(fixedRate = 5_000)
	public void startConsumer() {
		Executors.newSingleThreadExecutor().submit(() -> {
			Properties properties = new Properties();
			properties.setProperty(BOOTSTRAP_SERVERS_CONFIG, "localhost:9092");
			properties.setProperty(GROUP_ID_CONFIG, "test");
			properties.setProperty(KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class.getName());
			properties.setProperty(VALUE_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class.getName());

			try (Consumer<String, String> consumer = new KafkaConsumer<>(properties)) {
				consumer.subscribe(Arrays.asList("my-topic"));
				ConsumerRecords<String, String> records = consumer.poll(Duration.ofMillis(100));
				for (ConsumerRecord<String, String> record : records) {
					System.out.printf("Offset = %d, key = %s, value = %s%n",
							record.offset(), record.key(), record.value());
				}
			}
		});
	}

}
