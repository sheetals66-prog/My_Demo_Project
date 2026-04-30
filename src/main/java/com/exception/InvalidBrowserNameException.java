package com.exception;

public class InvalidBrowserNameException extends RuntimeException {
	String browsername;

	public InvalidBrowserNameException(String browsername) {
		this.browsername = browsername;
	}

	@Override
	public String getMessage() {
		return browsername + "Browser is not supported";

	}

}
