package com.orderms.order.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.orderms.order.common.ApplicationExceptionHandler;
import com.orderms.order.domain.ServiceResponse;

@ControllerAdvice
public class OrderExceptionController {

	@ExceptionHandler(value = ApplicationExceptionHandler.class)
	public ResponseEntity<Object> exception(ApplicationExceptionHandler applicationExceptionHandler) {
		ServiceResponse serviceResponse = new ServiceResponse();
		return new ResponseEntity<>(serviceResponse, HttpStatus.NOT_FOUND);
	}

}
