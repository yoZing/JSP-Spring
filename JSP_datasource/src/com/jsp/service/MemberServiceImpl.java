package com.jsp.service;

import java.sql.SQLException;
import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

import com.jsp.dao.MemberDAO;
import com.jsp.dto.MemberVO;

public class MemberServiceImpl implements MemberService {

	private SqlSessionFactory sqlSessionFactory;
	public void setSqlSessionFactory (SqlSessionFactory sqlSessionFactory) {
		this.sqlSessionFactory = sqlSessionFactory;
	}
	
	private MemberDAO memberDAO;
	public void setMemberDAO(MemberDAO memberDAO) {
		this.memberDAO = memberDAO;
	}
	
	@Override
	public List<MemberVO> getMemberList() throws SQLException {
		SqlSession session = sqlSessionFactory.openSession();
		try {
			List<MemberVO> memberList = memberDAO.selectMemberList(session);
			return memberList;
		} finally {
			session.close();
		}
		
	}

	@Override
	public MemberVO getMember(String id) throws SQLException {
		SqlSession session = sqlSessionFactory.openSession();
		MemberVO member = null;
		
		try {
			member = memberDAO.selectMemberById(session, id);
			return member;
		} finally {
			session.close();
		}
	}

	@Override
	public void regist(MemberVO mv) throws SQLException {
		SqlSession session = sqlSessionFactory.openSession();
		
		try {
			memberDAO.insertMember(session, mv);
		} finally {
			session.close();
		}
		
	}

	@Override
	public void modify(MemberVO mv) throws SQLException {
		SqlSession session = sqlSessionFactory.openSession();
		
		try {
			memberDAO.updateMember(session, mv);
		} finally {
			session.close();
		}
		
	}

	@Override
	public void remove(String id) throws SQLException {
		SqlSession session = sqlSessionFactory.openSession();

		try {
			memberDAO.deleteMember(session, id);
		} finally {
			session.close();
		}
	}
	

}