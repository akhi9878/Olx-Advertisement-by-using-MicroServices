package com.olx.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.BAD_REQUEST)
public class InvalidAdvertiseDeleteIdException extends RuntimeException {

      private String msg;
	
	 public InvalidAdvertiseDeleteIdException(){}
	
	
	public InvalidAdvertiseDeleteIdException(String msg){
		this.msg=msg;
	}
	
	public String toString() {
		return "Invalid Delete Advertise Id"+this.msg;
}

}