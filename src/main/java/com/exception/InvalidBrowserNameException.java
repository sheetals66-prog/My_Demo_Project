package com.exception;

//Custom exception class for invalid browser names
public class InvalidBrowserNameException extends RuntimeException {
	String browsername;

	// Constructor to store the invalid browser name
	public InvalidBrowserNameException(String browsername) {
		this.browsername = browsername;
	}

	// Override getMessage() to show custom error message
	@Override
	public String getMessage() {
		return browsername + "Browser is not supported";

	}

}
