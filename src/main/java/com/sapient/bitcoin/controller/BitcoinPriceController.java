package com.sapient.bitcoin.controller;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.RestController;

import com.sapient.bitcoin.bean.Request;
import com.sapient.bitcoin.bean.Response;
import com.sapient.bitcoin.service.BitcoinPriceService;

@RestController
public class BitcoinPriceController {

	private static final Logger LOGGER = LoggerFactory.getLogger(BitcoinPriceController.class);

	@Autowired
	private BitcoinPriceService bitcoinPriceService;

	@Autowired
	private RequestValidator requestValidator;

	@InitBinder
	private void initBinder(WebDataBinder binder) {
		binder.setValidator(requestValidator);
	}

	@GetMapping("/price/{startDate}/{endDate}/{currency}")
	public ResponseEntity<Response> getTeamStadings(@Valid Request request) {
		LOGGER.debug("Fetching currency for request : {}",request);
		Response response = bitcoinPriceService.fetchPrice(request);
		return new ResponseEntity<Response>(response, HttpStatus.OK);

	}

}
