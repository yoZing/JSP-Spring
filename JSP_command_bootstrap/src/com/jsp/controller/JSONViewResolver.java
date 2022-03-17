package com.jsp.controller;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletResponse;

import com.fasterxml.jackson.databind.ObjectMapper;

public class JSONViewResolver {
	
	public static void view(HttpServletResponse resp, Object target) throws Exception {
		
		// 출력
		ObjectMapper mapper = new ObjectMapper();
		
		// context Type 결정
		resp.setContentType("application/json;charset=utf-8");
		PrintWriter out = resp.getWriter();
		
		// 내보내기
		out.println(mapper.writeValueAsString(target));
		
		// out 객체 를 종료하고 환원.
		out.close();
	}
}