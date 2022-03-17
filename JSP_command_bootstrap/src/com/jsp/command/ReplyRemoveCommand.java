package com.jsp.command;

public class ReplyRemoveCommand {

	private String rno;
	private String bno;
	private String pno;
	private String page;
	
	public void setRno(String rno) {
		this.rno = rno;
	}
	public void setBno(String bno) {
		this.bno = bno;
	}
	public void setPno(String pno) {
		this.pno = pno;
	}
	public void setPage(String page) {
		this.page = page;
	}
	
	public int getRno() {
		return Integer.parseInt(rno);
	}
	public int getBno() {
		return Integer.parseInt(bno);
	}
	public int getPno() {
		return Integer.parseInt(pno);
	}
	public int getPage() {
		return Integer.parseInt(page);
	}
	
}
