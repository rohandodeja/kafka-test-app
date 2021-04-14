package com.rohan.kafkatest.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.rohan.kafkatest.dto.TransactionDto;
import com.rohan.kafkatest.service.ProducerService;

/**
 * 
 * @author Rohan Dodeja
 *
 */
@RestController
public class InEventRequestController {

	@Autowired
	private ProducerService producerService;

	@PostMapping(value = "/testController")
	public ResponseEntity<String> dummyRedisTestCommissionEvalDto(@RequestBody TransactionDto transactionDto) {

		producerService.pushToQueue(transactionDto);
		return new ResponseEntity<>(HttpStatus.OK);

	}

}
