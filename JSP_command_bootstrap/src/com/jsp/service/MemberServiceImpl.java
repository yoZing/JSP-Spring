package com.jsp.service;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

import com.jsp.command.Criteria;
import com.jsp.dao.MemberDAO;
import com.jsp.dto.MemberVO;

public class MemberServiceImpl implements MemberService {
	
	protected SqlSessionFactory sqlSessionFactory;
	public void setSqlSessionFactory(SqlSessionFactory sqlSessionFactory) {
		this.sqlSessionFactory = sqlSessionFactory;
	}
	
	protected MemberDAO memberDAO;
	public void setMemberDAO(MemberDAO memberDAO) {
		this.memberDAO = memberDAO;
	}
	

	@Override
	public List<MemberVO> getMemberList() throws Exception {
		SqlSession session= sqlSessionFactory.openSession(false);
		
		List<MemberVO> memberList = null;
		try {
			memberList = memberDAO.selectMemberList(session);
			
			session.commit();
			
		}catch(Exception e) {			
			session.rollback();
			e.printStackTrace();
			//......
			throw e;
		}finally {
			session.close();
		}
		
		return memberList;
	}
	
	@Override
	public List<MemberVO> getMemberList(Criteria cri) throws Exception {
		SqlSession session= sqlSessionFactory.openSession(false);
		
		List<MemberVO> memberList = null;
		try {
			memberList = memberDAO.selectMemberList(session, cri);
			
			session.commit();
			
		}catch(Exception e) {			
			session.rollback();
			e.printStackTrace();
			//......
			throw e;
		}finally {
			session.close();
		}
		
		return memberList;
	}
	
	
	
	
	@Override
	public MemberVO getMember(String id) throws Exception {
		SqlSession session = sqlSessionFactory.openSession();
		try {

			MemberVO member = memberDAO.selectMemberById(session, id);
			return member;
		} finally {
			session.close();
		}	
	}

	@Override
	public void regist(MemberVO member) throws Exception {
		SqlSession session = sqlSessionFactory.openSession();
		try {

			memberDAO.insertMember(session, member);
		} finally {
			session.close();
		}
		
	}

	@Override
	public void modify(MemberVO member) throws Exception {
		SqlSession session = sqlSessionFactory.openSession();
		try {

			memberDAO.updateMember(session, member);
		} finally {
			session.close();
		}

	}

	@Override
	public void remove(String id) throws Exception {
		SqlSession session = sqlSessionFactory.openSession();
		try {

			memberDAO.deleteMember(session, id);
		} finally {
			session.close();
		}
		
	}

	@Override
	public void enabled(String id, int enabled) throws Exception {
		SqlSession session = sqlSessionFactory.openSession();
		try {

			memberDAO.enabledMember(session, id, enabled);
		} finally {
			session.close();
		}
		
	}


}
