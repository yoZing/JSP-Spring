package com.jsp.service;

import java.sql.SQLException;
import java.util.List;

import com.jsp.dto.MemberVO;

public interface MemberService {
	
	// 회원리스트 조회
	List<MemberVO> getMemberList() throws SQLException;
	
	// 회원정보 조회
	MemberVO getMember(String id) throws SQLException;
	
	// 회원정보 등록
	public void regist(MemberVO mv) throws SQLException;
	
	// 회원정보 수정
	void modify(MemberVO mv) throws SQLException;
	
	// 회원정보 삭제
	void remove(String id) throws SQLException;
	
}
