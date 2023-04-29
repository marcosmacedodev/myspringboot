package com.myspringboot.controller.exception;

import java.util.ArrayList;
import java.util.List;

public class ValidationError extends ResponseError {

	private static final long serialVersionUID = 1L;
	
	List<FieldMessage> listError = new ArrayList<>();

	public ValidationError() {
		super();
		// TODO Auto-generated constructor stub
	}

	public ValidationError(Long timestamp, Integer status, String error, String message, String path) {
		super(timestamp, status, error, message, path);
		// TODO Auto-generated constructor stub
	}

	public List<FieldMessage> getListError() {
		return listError;
	}

	public void addError(FieldMessage fieldMessage) {
		listError.add(fieldMessage);
	}
	
	public void addError(String fieldname, String fieldMessage) {
		listError.add(new FieldMessage(fieldname, fieldMessage));
	}
	
	
}
