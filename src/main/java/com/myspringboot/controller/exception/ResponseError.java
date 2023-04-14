package com.myspringboot.controller.exception;

import java.io.Serializable;

public class ResponseError implements Serializable{

	private static final long serialVersionUID = 1L;
	
	private String message;
	private Integer status;
	private Long timestamp;
	
	public ResponseError() {
		super();
	}

	public ResponseError(String message, Integer status, Long timestamp) {
		super();
		this.message = message;
		this.status = status;
		this.timestamp = timestamp;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public Long getTimestamp() {
		return timestamp;
	}

	public void setTimestamp(Long timestamp) {
		this.timestamp = timestamp;
	}
}
