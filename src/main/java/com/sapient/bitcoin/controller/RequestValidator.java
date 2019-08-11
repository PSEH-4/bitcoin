package com.sapient.bitcoin.controller;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import com.sapient.bitcoin.bean.Request;

@Component
public class RequestValidator implements Validator {

	@Override
	public boolean supports(Class<?> clazz) {
		return Request.class.isAssignableFrom(clazz);
	}

	@Override
	public void validate(Object target, Errors errors) {

		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");

		Request request = (Request) target;
		ValidationUtils.rejectIfEmpty(errors, "currency", "currency.empty", "Currency can't be empty");
		ValidationUtils.rejectIfEmpty(errors, "startDate", "startDate.empty", "startDate can't be empty");
		ValidationUtils.rejectIfEmpty(errors, "endDate", "endDate.empty", "endDate can't be empty");

		Date start = null;
		Date end = null;
		try {
			start = dateFormat.parse(request.getStartDate());
			end = dateFormat.parse(request.getEndDate());
		} catch (ParseException e) {
			errors.rejectValue("startDate", "startDate.invalid", "Start Date Must be Before EndDate");
		}

		if (start.after(end)) {
			errors.rejectValue("startDate", "startDate.invalid", "Start Date Must be Before EndDate");
		}

	}

}