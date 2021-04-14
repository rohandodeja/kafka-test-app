package com.rohan.kafkatest.config;

import java.util.HashMap;
import java.util.Map;

import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.annotation.EnableKafka;
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory;
import org.springframework.kafka.core.ConsumerFactory;
import org.springframework.kafka.core.DefaultKafkaConsumerFactory;
import org.springframework.kafka.listener.ContainerProperties.AckMode;

/**
 * @author Rohan Dodeja
 *
 */
@Configuration
@EnableKafka
public class KafkaConsumerConfig {

	@Value("${kafka.consumer.bootstrap.servers}")
	private String kafkaINStreamConsumerBootstrapServers;
	@Value("${kafka.consumer.key.deserializer.classname}")
	private String keySerialiser;
	@Value("${kafka.consumer.value.deserializer.classname}")
	private String valueSerialiser;
	@Value("${kafka.consumer.auto.offset.reset}")
	private String autoOffsetReset;
	@Value("${kafka.consumer.auto.commit}")
	private String autoCommit;
	

	public ConsumerFactory<String, String> kafkaConsumerFactory() {
		Map<String, Object> props = new HashMap<>();
		props.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, kafkaINStreamConsumerBootstrapServers);
		props.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, keySerialiser);
		props.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, valueSerialiser);
		props.put(ConsumerConfig.ENABLE_AUTO_COMMIT_CONFIG, autoCommit);
		props.put(ConsumerConfig.AUTO_OFFSET_RESET_CONFIG, autoOffsetReset);
	

		return new DefaultKafkaConsumerFactory<>(props);
	}

	@Bean
	public ConcurrentKafkaListenerContainerFactory<String, String> kafkaPoolListenerContainerFactory() {
		ConcurrentKafkaListenerContainerFactory<String, String> factory = new ConcurrentKafkaListenerContainerFactory<>();
		factory.setConsumerFactory(kafkaConsumerFactory());
		factory.getContainerProperties().setAckMode(AckMode.MANUAL_IMMEDIATE);
		factory.setMissingTopicsFatal(false);
		return factory;
	}

}
