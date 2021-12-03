package com.olx.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.BAD_REQUEST)
public class InvalidAdvertiseIdException extends RuntimeException{
	
	private String msg;
	
	public InvalidAdvertiseIdException(){}
	
	
	public InvalidAdvertiseIdException(String msg){
		this.msg=msg;
	}
	
	public String toString() {
		return "Invalid Advertise Id "+ this.msg;
	}
	

}
