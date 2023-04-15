package com.myspringboot.controller.exception;

import java.io.Serializable;

public class ResponseError implements Serializable{

	private static final long serialVersionUID = 1L;
	
	private String error;
	private Integer status;
	private Long timestamp;
	private String path;
	
	public ResponseError() {
		super();
	}

	public ResponseError(String error, Integer status, Long timestamp, String path) {
		super();
		this.error = error;
		this.status = status;
		this.timestamp = timestamp;
		this.path = path;
	}

	public String getError() {
		return error;
	}

	public void setError(String message) {
		this.error = message;
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

	public String getPath() {
		return path;
	}

	public void setPath(String path) {
		this.path = path;
	}
	
	
}
