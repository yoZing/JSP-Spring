package com.spring.controller;

import java.io.File;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class FileUploadController {
	
	@RequestMapping("/fileUploadForm")
	public void fileUploadForm() {}
	
	@RequestMapping(value = "/multipartFile", method = RequestMethod.POST, produces = "text/plain;charset=utf-8")
	public ModelAndView uploadedByMultipartFile(@RequestParam(defaultValue ="제목없음") String title, @RequestParam("file") MultipartFile multi, ModelAndView mnv) throws Exception{
		
		String uploadPath = "c:\\upload\\file";
		
		String fileName = multi.getOriginalFilename();
		
		File file = new File(uploadPath, fileName);
		
		file.mkdirs();
		
		multi.transferTo(file);
		
		mnv.addObject("title", title);
		mnv.addObject("originalFileName", fileName);
		mnv.addObject("uploadedFileName", file.getName());
		mnv.addObject("uploadPath", file.getAbsolutePath());
		
		mnv.setViewName("fileUploaded");
		
		return mnv;
	}
	
	@RequestMapping(value = "multipartHttpServletRequest", method = RequestMethod.POST, produces = "text/plain;charset=utf-8")
	public ModelAndView multipartHttpServletRequest(MultipartHttpServletRequest request, ModelAndView mnv) throws Exception{
		
		String title = request.getParameter("title");
		MultipartFile file = request.getFile("file");
		
		mnv = uploadedByMultipartFile(title, file, mnv);
		
		return mnv;
	}
	
	@RequestMapping(value = "commandObject", method = RequestMethod.POST, produces = "text/plain;charset=utf8")
	public ModelAndView uploadByCommandObject(MultiPartCommand command, ModelAndView mnv) throws Exception{
		
		String title = command.getTitle();
		MultipartFile file = command.getFile();
		
		mnv = uploadedByMultipartFile(title, file, mnv);
		
		return mnv;
	}
}

class MultiPartCommand {
	
	private String title;
	private MultipartFile file;
	
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public MultipartFile getFile() {
		return file;
	}
	public void setFile(MultipartFile file) {
		this.file = file;
	}
}