package com.sapient.bitcoin.exception;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {

	private static final Logger LOGGER = LoggerFactory.getLogger(GlobalExceptionHandler.class);

	@ExceptionHandler(Exception.class)
	public ResponseEntity<Error> otherException(Exception ex) {
		LOGGER.error("Exception occured : {}", ex);
		Error error = new Error(ex.getMessage(), HttpStatus.SERVICE_UNAVAILABLE.value());
		return new ResponseEntity<Error>(error, HttpStatus.SERVICE_UNAVAILABLE);
	}

	@ExceptionHandler(BindException.class)
	public ResponseEntity<Error> bindException(BindException ex) {
		LOGGER.error("Exception occured : {}", ex.getMessage());
		StringBuilder builder = new StringBuilder();
		ex.getAllErrors().forEach(oe -> {
			builder.append(oe.getDefaultMessage());
		});
		Error error = new Error(builder.toString(), HttpStatus.BAD_REQUEST.value());
		return new ResponseEntity<Error>(error, HttpStatus.BAD_REQUEST);
	}

	@ExceptionHandler(BitcoinApiException.class)
	public ResponseEntity<Error> apiException(BitcoinApiException ex) {
		LOGGER.error("Exception occured : {}", ex.getMessage());
		Error error = new Error(ex.getMessage(), HttpStatus.SERVICE_UNAVAILABLE.value());
		return new ResponseEntity<Error>(error, HttpStatus.SERVICE_UNAVAILABLE);
	}

}
