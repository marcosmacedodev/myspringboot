package com.myspringboot.services.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class FileException extends RuntimeException{
	
	private static final long serialVersionUID = 1L;

	public FileException() {
		super();
	}
	
	public FileException(String message) {
		super(message);
	}
	
	public FileException(String message, Throwable throwable) {
		super(message, throwable);
	}

}
