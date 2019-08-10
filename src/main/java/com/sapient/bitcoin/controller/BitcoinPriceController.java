package com.sapient.bitcoin.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.sapient.bitcoin.bean.Error;
import com.sapient.bitcoin.bean.Response;
import com.sapient.bitcoin.repository.BitcoinApiException;
import com.sapient.bitcoin.service.BitcoinPriceService;

@RestController
public class BitcoinPriceController {

	@Autowired
	private BitcoinPriceService bitcoinPriceService;

	@GetMapping("/price/{startDate}/{endDate}/{currency}")
	public ResponseEntity<Response> getTeamStadings(
			@PathVariable(value = "startDate", required = true) String startDate,
			@PathVariable(value = "endDate", required = true) String endDate,
			@PathVariable(value = "currency", required = true) String currency) {
		Response response = bitcoinPriceService.fetchPrice(startDate, endDate,
				currency);
		return new ResponseEntity<Response>(response, HttpStatus.OK);

	}

	@ExceptionHandler(Exception.class)
	public ResponseEntity<Error> handleException(Exception ex) {
		com.sapient.bitcoin.bean.Error error = new Error(ex.getMessage(), HttpStatus.SERVICE_UNAVAILABLE.value()); 
		return new ResponseEntity<Error>(error, HttpStatus.SERVICE_UNAVAILABLE);
	}
	
	@ExceptionHandler(BitcoinApiException.class)
	public ResponseEntity<Error> handleException(BitcoinApiException ex) {
		com.sapient.bitcoin.bean.Error error = new Error(ex.getMessage(), HttpStatus.SERVICE_UNAVAILABLE.value()); 
		return new ResponseEntity<Error>(error, HttpStatus.SERVICE_UNAVAILABLE);
	}

}
