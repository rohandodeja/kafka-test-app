package com.rohan.kafkatest.service;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.support.Acknowledgment;

import com.rohan.kafkatest.dto.TransactionDto;

import lombok.extern.slf4j.Slf4j;

/**
 * @author Rohan Dodeja
 *
 */
@Slf4j
@Configuration
public class ConsumerService {

	@KafkaListener(id = "CommissionTestConsumer", topics = "${kafka.consumer.queueName}", containerFactory = "kafkaPoolListenerContainerFactory", groupId = "${kafka.consumer.groupId}")
	public void listen(ConsumerRecord<?, ?> consumerRecord, Acknowledgment ack) throws InterruptedException {
		TransactionDto transactionDto = null;
		String key = null;
		Long offset = null;
		try {
			transactionDto = (TransactionDto) consumerRecord.value();
			key = (String) consumerRecord.key();
			offset = consumerRecord.offset();
			display(transactionDto);
			ack.acknowledge();
			log.info("Processed record having key:" + key + " on offset:" + offset);
		} catch (Exception e) {
			log.error("There is an issue {}", e);
			e.printStackTrace();
		}

	}

	private void display(TransactionDto transactionDto) throws Exception {
		System.err.println(transactionDto);
		if (transactionDto.getData().equals("1")) {
			throw new Exception();
		}
	}

}
