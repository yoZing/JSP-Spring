package com.jsp.exception;

public class IdNotFoundException extends RuntimeException {
	
	public IdNotFoundException() {
		super("아이디가 존재하지 않습니다.");
	}

}
