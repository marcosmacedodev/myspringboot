package com.myspringboot.controller.exception;

import javax.servlet.http.HttpServletRequest;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.myspringboot.services.exceptions.AuthorizationException;
import com.myspringboot.services.exceptions.FileException;
import com.myspringboot.services.exceptions.ObjectNotFoundException;

@ControllerAdvice
public class ControllerExceptionHandle {
	
	@ExceptionHandler(ObjectNotFoundException.class)
	public ResponseEntity<ResponseError> objectNotFound(ObjectNotFoundException e, HttpServletRequest request){
		
		ResponseError err = new ResponseError(e.getMessage(), HttpStatus.NOT_FOUND.value(), System.currentTimeMillis(), request.getRequestURI());
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(err);
	}
	
	@ExceptionHandler(DataIntegrityViolationException.class)
	public ResponseEntity<ResponseError> dataIntegrityViolation(DataIntegrityViolationException e, HttpServletRequest request) {
		
		ResponseError err = new ResponseError("Não é possível excluir este objeto, existem outros objetos vínculados a ele.", HttpStatus.INTERNAL_SERVER_ERROR.value(), System.currentTimeMillis(), request.getRequestURI());
		return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(err);
		
	}

	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity<ResponseError> methodArgumentNotValid(MethodArgumentNotValidException e, HttpServletRequest request){
		
		ValidationError err = new ValidationError("Erro de validação", HttpStatus.BAD_REQUEST.value(), System.currentTimeMillis(), request.getRequestURI());
		for(FieldError fieldErr : e.getBindingResult().getFieldErrors()) 
		{
			err.addError(fieldErr.getField(), fieldErr.getDefaultMessage());
		}
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(err);
	}
	
	@ExceptionHandler(AuthorizationException.class)
	public ResponseEntity<ResponseError> authorization(AuthorizationException e, HttpServletRequest request){
		
		ResponseError err = new ResponseError(e.getMessage(), HttpStatus.FORBIDDEN.value(), System.currentTimeMillis(), request.getRequestURI());
		return ResponseEntity.status(HttpStatus.FORBIDDEN).body(err);
	}
	
	@ExceptionHandler(FileException.class)
	public ResponseEntity<ResponseError> file(FileException e, HttpServletRequest request){
		ResponseError err = new ResponseError(e.getMessage(), HttpStatus.BAD_REQUEST.value(), System.currentTimeMillis(), request.getRequestURI());
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(err);
	}
	
}
