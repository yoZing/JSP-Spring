package kr.or.ddit.dao;

import java.sql.SQLException;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import com.jsp.dto.MemberVO;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations="classpath:kr/or/ddit/context/root-context.xml")
@Transactional
public class TestMemberDAOBeanImpl {

	@Autowired
	private MemberDAOBean memberDAOBean;
	
	@Test
	public void testSelectMember() throws Exception {
		String id = "woo";
		MemberVO member = memberDAOBean.selectMemberById(id);
		
		Assert.assertEquals(id, member.getId());
	}
	
	@Test
	@Rollback
	public void testInsertMember() throws SQLException {
		MemberVO testMember = new MemberVO();
		testMember.setId("kaka");
		testMember.setPwd("pwd");
		testMember.setName("kaka");
		testMember.setEmail("kaka@kaka.net");
		testMember.setPhone("000-0000-0000");
		testMember.setPicture("noImage.jsp");
		testMember.setAuthority("ROLE_USER");
		
		memberDAOBean.insertMember(testMember);
		
		MemberVO result = memberDAOBean.selectMemberById(testMember.getId());
		
		Assert.assertEquals(testMember.getId(), result.getId());
		
	}
	
	
}
