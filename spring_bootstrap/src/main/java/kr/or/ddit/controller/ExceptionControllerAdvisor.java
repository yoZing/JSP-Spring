package kr.or.ddit.controller;

import java.sql.SQLException;

import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.jsp.dto.MemberVO;

@ControllerAdvice
public class ExceptionControllerAdvisor {
	
	private static final Logger logger = LoggerFactory.getLogger(ExceptionControllerAdvisor.class);
	
	@ExceptionHandler(SQLException.class)
	public String sqlExceptionPage(Exception e, Model model, HttpSession session) {
		String url = "error/sqlException";
		
		logger.error(e.toString());
		
		MemberVO loginUser = (MemberVO) session.getAttribute("loginUser");
		
		model.addAttribute("exception", e);
		model.addAttribute("user", loginUser != null ? loginUser.getName() : "");
		
		return url;
	}
	
	@ExceptionHandler(Exception.class)
	public String exceptionPage(Exception e, Model modle, HttpSession session) {
		String url = "error/500";
		
		logger.error(e.toString());
		
		return url;
	}
	
}
