package com.jsp.dao;

import java.sql.SQLException;
import java.util.List;

import org.apache.ibatis.session.SqlSession;

import com.jsp.dto.MemberVO;

public class MemberDAOImpl implements MemberDAO {

	@Override
	public List<MemberVO> selectMemberList(SqlSession session) throws SQLException {
		
		List<MemberVO> memberList = session.selectList("Member-Mapper.selectMemberList");
		
		return memberList;
	}

	@Override
	public MemberVO selectMemberById(SqlSession session, String id) throws SQLException {
		
		MemberVO member = session.selectOne("Member-Mapper.selectMemberById", id);
		
		return member;
	}

	@Override
	public int insertMember(SqlSession session, MemberVO mv) throws SQLException {
		
		int cnt = session.insert("Member-Mapper.insertMember", mv);
		
		return cnt;
	}

	@Override
	public int updateMember(SqlSession session, MemberVO mv) throws SQLException {
		
		int cnt = session.update("Member-Mapper.updateMember", mv);
		
		return cnt;
	}

	@Override
	public int deleteMember(SqlSession session, String id) throws SQLException {
		
		int cnt = session.delete("Member-Mapper.deleteMember", id);
		
		return cnt;
	}

}
