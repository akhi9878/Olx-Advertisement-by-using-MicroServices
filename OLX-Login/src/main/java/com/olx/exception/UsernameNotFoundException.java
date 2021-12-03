package com.olx.exception;

public class UsernameNotFoundException extends RuntimeException {

	private String message = "";
	public UsernameNotFoundException() {}
	public UsernameNotFoundException(String message) {
		this.message = message;
	}
	@Override
	public String toString() {
		return "Username Not Found " + message;
	}
}
