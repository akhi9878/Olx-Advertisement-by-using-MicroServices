package com.olx.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.UNAUTHORIZED)
public class InvalidTokenException extends RuntimeException {
	
	private String message;
	public InvalidTokenException() {
		this.message = "";
	}
	public InvalidTokenException(String message) {
		this.message = message;
	}
	public String toString() {
		return "Invalid Token: " + this.message;
	}

}
