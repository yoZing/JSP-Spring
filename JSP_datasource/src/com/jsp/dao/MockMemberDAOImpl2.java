package com.jsp.dao;

import java.sql.SQLException;
import java.util.List;

import org.apache.ibatis.session.SqlSession;

import com.jsp.dto.MemberVO;

public class MockMemberDAOImpl2 implements MemberDAO {

	@Override
	public List<MemberVO> selectMemberList(SqlSession session) throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public MemberVO selectMemberById(SqlSession session, String id) throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int insertMember(SqlSession session, MemberVO mv) throws SQLException {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int updateMember(SqlSession session, MemberVO mv) throws SQLException {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int deleteMember(SqlSession session, String id) throws SQLException {
		// TODO Auto-generated method stub
		return 0;
	}
}
