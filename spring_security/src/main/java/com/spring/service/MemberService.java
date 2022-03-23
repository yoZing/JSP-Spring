package com.spring.service;

import java.sql.SQLException;

import com.jsp.dto.MemberVO;

public interface MemberService {
	
	public MemberVO getMember(String id) throws SQLException;
	
}
