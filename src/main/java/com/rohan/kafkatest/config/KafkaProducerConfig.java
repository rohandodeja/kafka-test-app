package com.rohan.kafkatest.config;

import java.util.HashMap;
import java.util.Map;

import org.apache.kafka.clients.producer.ProducerConfig;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.core.DefaultKafkaProducerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.core.ProducerFactory;

import com.rohan.kafkatest.dto.TransactionDto;

/**
 * @author Rohan Dodeja
 *
 */
@Configuration
public class KafkaProducerConfig {

	@Value("${kafka.producer.bootstrap.servers}")
	private String kafkaProducerBootstrapServers;
	@Value("${kafka.producer.key.serialiser.classname}")
	private String keySerialiser;
	@Value("${kafka.producer.value.serialiser.classname}")
	private String valueSerialiser;

	public Map<String, Object> producerConfig() {
		Map<String, Object> props = new HashMap<>();
		props.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, kafkaProducerBootstrapServers);
		props.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, keySerialiser);
		props.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, valueSerialiser);
		return props;
	}

	@Bean
	ProducerFactory<String, TransactionDto> producerFactory() {
		return new DefaultKafkaProducerFactory<>(producerConfig());

	}

	@Bean(name = "kafkaProducerTemplate")
	KafkaTemplate<String, TransactionDto> kafkaProducerTemplate() {
		return new KafkaTemplate<String, TransactionDto>(producerFactory());
	}

}
