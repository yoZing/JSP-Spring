package com.jsp.service;

import java.sql.SQLException;

import org.apache.ibatis.session.SqlSession;

import com.jsp.dto.MemberVO;
import com.jsp.exception.IdNotFoundException;
import com.jsp.exception.InvalidPasswordException;

public class LoginSearchMemberServiceImpl extends SearchMemberServiceImpl
										  implements LoginSearchMemberService {

	@Override
	public void login(String id, String pwd) throws IdNotFoundException, InvalidPasswordException, SQLException {
		
		SqlSession session = sqlSessionFactory.openSession();
		try {
			
			MemberVO member = memberDAO.selectMemberById(session, id);
			if (member == null)
				throw new IdNotFoundException();
			if (!pwd.equals(member.getPwd()))
				throw new InvalidPasswordException();
				
		} finally {
			session.close();
		}
		
	}

	
}
