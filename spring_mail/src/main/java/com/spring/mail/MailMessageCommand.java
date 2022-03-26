package com.spring.mail;

import org.springframework.web.multipart.MultipartFile;

public class MailMessageCommand {
	
	private String sender;
	private String reveiver;
	private String title;
	private String content;
	private MultipartFile file;
	public String getSender() {
		return sender;
	}
	public void setSender(String sender) {
		this.sender = sender;
	}
	public String getReveiver() {
		return reveiver;
	}
	public void setReveiver(String reveiver) {
		this.reveiver = reveiver;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public MultipartFile getFile() {
		return file;
	}
	public void setFile(MultipartFile file) {
		this.file = file;
	}
	
	
}
