package com.rohan.kafkatest.config;

import java.io.IOException;
import java.util.Map;

import org.apache.kafka.common.serialization.Deserializer;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.rohan.kafkatest.dto.TransactionDto;

import lombok.extern.slf4j.Slf4j;

/**
 * 
 * @author Rohan Dodeja
 *
 * @param <T>
 */
@Slf4j
public class KafkaCustomDeserializer<T> implements Deserializer<T> {

	@Override
	public void configure(Map<String, ?> configs, boolean isKey) {

	}

	@SuppressWarnings("unchecked")
	@Override
	public T deserialize(String topic, byte[] data) {
		TransactionDto value = null;
		try {
			value = new ObjectMapper().readValue(data, TransactionDto.class);
		} catch (IOException e) {
			log.info("Exception: {}", e.getMessage());
		}
		return (T) value;
	}

	@Override
	public void close() {

	}

}
