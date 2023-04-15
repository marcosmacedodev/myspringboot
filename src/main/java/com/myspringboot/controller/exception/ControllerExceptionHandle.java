package com.myspringboot.controller.exception;

import javax.servlet.http.HttpServletRequest;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.myspringboot.services.exceptions.ObjectNotFoundException;

@ControllerAdvice
public class ControllerExceptionHandle {
	
	@ExceptionHandler
	public ResponseEntity<ResponseError> objectNotFound(ObjectNotFoundException e, HttpServletRequest request){
		
		ResponseError err = new ResponseError(e.getMessage(), HttpStatus.NOT_FOUND.value(), System.currentTimeMillis(), request.getRequestURI());
		
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(err);
	}
	
//	@ExceptionHandler
//	public ResponseEntity<ResponseError> dataIntegrity(DataIntegrityException e, HttpServletRequest request){
//		ResponseError err = new ResponseError(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR.value(), System.currentTimeMillis());
//		
//		return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(err);
//	}
	@ExceptionHandler
	public ResponseEntity<ResponseError> dataIntegrityViolation(DataIntegrityViolationException e, HttpServletRequest request) {
		
		ResponseError err = new ResponseError("Não é possível excluir este objeto, existem outros objetos vínculados a ele.", HttpStatus.INTERNAL_SERVER_ERROR.value(), System.currentTimeMillis(), request.getRequestURI());
		
		return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(err);
		
	}
	
	@ExceptionHandler
	public ResponseEntity<ResponseError> httpRequestMethodNotSupported(HttpRequestMethodNotSupportedException e, HttpServletRequest request) {
		
		ResponseError err = new ResponseError("Método não permitido", HttpStatus.METHOD_NOT_ALLOWED.value(), System.currentTimeMillis(), request.getRequestURI());
		
		return ResponseEntity.status(HttpStatus.METHOD_NOT_ALLOWED).body(err);
		
	}
}
