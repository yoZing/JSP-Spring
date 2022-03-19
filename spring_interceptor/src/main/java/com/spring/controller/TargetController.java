package com.spring.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class TargetController {
	
	@RequestMapping(value = "/target")
	public String test(int num, Model model) throws Exception {
		String url = "test/main";
		
		if (num == 1) {
			throw new Exception("from TestController");
		}
		
		model.addAttribute("message", "To the Victory!");
		
		return url;
	}
}
