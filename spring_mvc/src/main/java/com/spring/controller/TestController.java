package com.spring.controller;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class TestController {

	@RequestMapping(value = "/test", method = RequestMethod.GET)
	public String test() {
		String url = "test/main";

		return url;
	}

	@RequestMapping(value = "/test/param", method = RequestMethod.GET)
	public String testParam(HttpServletRequest req, HttpServletResponse resp) {
		String url = "test/main";

		String message = (String) req.getParameter("message");

		req.setAttribute("message", message);

		return url;
	}

	@RequestMapping(value = "/test2/param", method = RequestMethod.GET)
	public String test2Param(String message, Model model) {
		String url = "test/main";

		model.addAttribute("message", message);

		return url;
	}

	@RequestMapping(value = "/test3/param", method = RequestMethod.GET)
	public String test3Param(@ModelAttribute("message") String message) {
		String url = "test/main";

		return url;
	}

	@RequestMapping(value = "/test/main", method = RequestMethod.GET)
	public void test4Param(@ModelAttribute("message") String message) {

	}

	@RequestMapping(value = "/test5/param", method = RequestMethod.GET)
	public String test5Param(@RequestParam(name = "message", defaultValue = "Have a nice Day!!") String msg,
			Model model) {
		String url = "test/main";

		model.addAttribute("message", msg);

		return url;
	}

//	@RequestMapping(value="/test6/param", method=RequestMethod.GET)
//	public void test6Param(int a, String b, int c, Object d, boolean k) {
//		System.out.println(a + " : " + b + " : " + c + " : " + d + " : " + k);
//	}
	@RequestMapping(value = "/test6/param", method = RequestMethod.GET)
	public void test6Param(CommandObject command, HttpServletResponse resp) throws Exception {

		PrintWriter out = resp.getWriter();
		out.println(command.a + " : " + command.b + " : " + command.c + " : " + command.d + " : " + command.k);
	}

}

class CommandObject {

	int a;
	String b;
	int c;
	Object d;
	boolean k;

	public int getA() {
		return a;
	}

	public void setA(int a) {
		this.a = a;
	}

	public String getB() {
		return b;
	}

	public void setB(String b) {
		this.b = b;
	}

	public int getC() {
		return c;
	}

	public void setC(int c) {
		this.c = c;
	}

	public Object getD() {
		return d;
	}

	public void setD(Object d) {
		this.d = d;
	}

	public boolean isK() {
		return k;
	}

	public void setK(boolean k) {
		this.k = k;
	}

}
