package com.spring.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class TestController2 {

	@RequestMapping(value = "/test2/main", method = RequestMethod.GET)
	public String test() {
		String url = "test/main";
		
		return url;
	}
	
	@RequestMapping(value = "/test2/param", method = RequestMethod.GET)
	public String test2Param(HttpServletRequest request, HttpServletResponse response) {
		String url = "test/main";
		
		String message = request.getParameter("message");
		
		request.setAttribute("message", message);
		
		return url;
	}
	
	@RequestMapping(value = "/test2/param2", method = RequestMethod.GET)
	public String test2Param(String message, Model model) {
		String url = "test/main";
		
		model.addAttribute("message", message);
		
		return url;
	}
	
	@RequestMapping(value = "test2/param3", method = RequestMethod.GET)
	public String test3Param(@ModelAttribute("message") String msg) {
		String url = "test/main";
		
		return url;
	}
	
	@RequestMapping(value = "test/main", method = RequestMethod.GET)
	public void test4Param() {
		
	}
}
