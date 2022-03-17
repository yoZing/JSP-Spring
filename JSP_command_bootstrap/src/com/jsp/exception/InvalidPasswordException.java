package com.jsp.exception;

public class InvalidPasswordException extends RuntimeException {
	
	public InvalidPasswordException() {
		super("패스워드가 일치하지 않습니다.");
	}

}
