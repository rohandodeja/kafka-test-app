package com.rohan.kafkatest.config;

import java.util.Map;

import org.apache.kafka.common.serialization.Serializer;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import lombok.extern.slf4j.Slf4j;

/**
 * 
 * @author Rohan Dodeja
 *
 * @param <T>
 */
@Slf4j
public class KafkaCustomSerializer<T extends Object> implements Serializer<T> {

	byte[] byteArrayValue;

	@Override
	public void configure(Map<String, ?> configs, boolean isKey) {
	}

	@Override
	public byte[] serialize(String topic, T data) {
		byte[] serializedData = null;
		try {
//			log.info("Data : {}", data);
			serializedData = new ObjectMapper().writeValueAsBytes(data);

		} catch (JsonProcessingException e) {
			log.info("Exception occured {}", e.getMessage());
		}
//		log.info("serialized data: {}", serializedData);
		return serializedData;
	}

	@Override
	public void close() {
	}
}
