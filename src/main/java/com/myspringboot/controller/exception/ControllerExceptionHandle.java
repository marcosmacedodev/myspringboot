package com.myspringboot.controller.exception;

import javax.servlet.http.HttpServletRequest;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.myspringboot.services.exceptions.ObjectNotFoundException;

@ControllerAdvice
public class ControllerExceptionHandle {
	
	@ExceptionHandler
	public ResponseEntity<ResponseError> objectNotFound(ObjectNotFoundException e, HttpServletRequest request){
		
		ResponseError err = new ResponseError(e.getMessage(), HttpStatus.NOT_FOUND.value(), System.currentTimeMillis());
		
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(err);
	}

}
