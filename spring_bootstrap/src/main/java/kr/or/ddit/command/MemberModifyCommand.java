package kr.or.ddit.command;

import org.springframework.web.multipart.MultipartFile;

import com.jsp.dto.MemberVO;

public class MemberModifyCommand {
	private String id;					// 아이디
	private String pwd;					// 패스워드
	private String name;				// 이름
	private String phone;				// 전화번호
	private String email;				// 이메일
	private String authority;			// 권한
	private MultipartFile picture;		// 사진파일
	private String uploadPicture;		// 변경된 사진 파일명
	
	public final String getId() {
		return id;
	}
	public final void setId(String id) {
		this.id = id;
	}
	public final String getPwd() {
		return pwd;
	}
	public final void setPwd(String pwd) {
		this.pwd = pwd;
	}
	public final String getName() {
		return name;
	}
	public final void setName(String name) {
		this.name = name;
	}
	public final String getPhone() {
		return phone;
	}
	public final void setPhone(String phone) {
		this.phone = phone;
	}
	public final String getEmail() {
		return email;
	}
	public final void setEmail(String email) {
		this.email = email;
	}
	public final String getAuthority() {
		return authority;
	}
	public final void setAuthority(String authority) {
		this.authority = authority;
	}
	public final MultipartFile getPicture() {
		return picture;
	}
	public final void setPicture(MultipartFile picture) {
		this.picture = picture;
	}
	public final String getUploadPicture() {
		return uploadPicture;
	}
	public final void setUploadPicture(String uploadPicture) {
		this.uploadPicture = uploadPicture;
	}
	
	public MemberVO toMemberVO() {
		MemberVO member = new MemberVO();
		member.setId(id);
		member.setPwd(pwd);
		member.setName(name);
		member.setPhone(phone);
		member.setEmail(email);
		member.setAuthority(authority);
		
		return member;
	}

}
