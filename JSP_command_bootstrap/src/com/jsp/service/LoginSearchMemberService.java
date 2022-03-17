package com.jsp.service;

import java.sql.SQLException;

import com.jsp.exception.IdNotFoundException;
import com.jsp.exception.InvalidPasswordException;

public interface LoginSearchMemberService extends SearchMemberService {

	void login(String id, String pwd) throws IdNotFoundException, InvalidPasswordException, SQLException;
	
}
