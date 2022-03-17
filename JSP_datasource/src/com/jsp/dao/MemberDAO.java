package com.jsp.dao;

import java.sql.SQLException;
import java.util.List;

import org.apache.ibatis.session.SqlSession;

import com.jsp.dto.MemberVO;

public interface MemberDAO {

	// 회원리스트 조회
	List<MemberVO> selectMemberList(SqlSession session) throws SQLException;
	
	
	// 회원정보 조회
	MemberVO selectMemberById(SqlSession session, String id) throws SQLException;
	
	// 회원정보 등록
	int insertMember(SqlSession session, MemberVO mv) throws SQLException;

	// 회원정보 수정
	int updateMember(SqlSession session, MemberVO mv) throws SQLException;
	
	// 회원정보 삭제
	int deleteMember(SqlSession session, String id) throws SQLException;
	
}
