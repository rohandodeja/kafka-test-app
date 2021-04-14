package com.rohan.kafkatest.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.stereotype.Service;
import org.springframework.util.concurrent.ListenableFuture;

import com.rohan.kafkatest.dto.TransactionDto;

import lombok.extern.slf4j.Slf4j;

/**
 * 
 * @author Rohan Dodeja
 *
 */
@Slf4j
@Service
public class ProducerService {

	@Value("${kafka.producer.queueName}")
	private String kafkaProducerQueueName;

	@Autowired
	@Qualifier("kafkaProducerTemplate")
	private KafkaTemplate<String, TransactionDto> kafkaProducerTemplate;

	public void pushToQueue(TransactionDto transactionDto) {

		ListenableFuture<SendResult<String, TransactionDto>> kafkaResult = kafkaProducerTemplate
				.send(kafkaProducerQueueName, transactionDto.getData(), transactionDto);
		SendResult<String, TransactionDto> kafkaSendResult;
		try {
			kafkaSendResult = kafkaResult.get();

			log.info("pushed to queue {}", kafkaSendResult);

		} catch (Exception e) {

			e.printStackTrace();
		}

	}

}
