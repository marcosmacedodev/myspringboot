package com.myspringboot.controller.exception;

import java.util.ArrayList;
import java.util.List;

public class ValidationError extends ResponseError {

	private static final long serialVersionUID = 1L;
	
	List<FieldMessage> listError = new ArrayList<>();
	
	public ValidationError(String error, Integer status, Long timestamp, String requestURI) {
		super(error, status, timestamp, requestURI);
	}

	public List<FieldMessage> getErrors() {
		return listError;
	}
	
	public void addError(String fieldName, String message) 
	{
		this.listError.add(new FieldMessage(fieldName, message));
	}
	
	public void addError(FieldMessage fieldMessage) 
	{
		this.listError.add(fieldMessage);
	}
}
