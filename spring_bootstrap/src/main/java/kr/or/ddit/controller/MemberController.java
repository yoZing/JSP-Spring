package kr.or.ddit.controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.SQLException;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.jsp.command.Criteria;
import com.jsp.command.MemberRegistCommand;
import com.jsp.dto.MemberVO;
import com.jsp.service.LoginSearchMemberService;
import com.jsp.util.MakeFileName;

import kr.or.ddit.command.MemberModifyCommand;

@Controller
@RequestMapping("/member")
public class MemberController {

	@Autowired
	private LoginSearchMemberService memberService;

	@Resource(name = "picturePath")
	private String picturePath;

	@RequestMapping(value = "/main")
	public void main() {
	}

	@RequestMapping("/list")
	public ModelAndView list(Criteria cri, ModelAndView mnv) throws SQLException {

		String url = "member/list";

		Map<String, Object> dataMap = null;

		try {
			dataMap = memberService.getSearchMemberList(cri);

		} catch (SQLException e) {
			e.printStackTrace();
			throw new SQLException();
		} catch (Exception e) {
			e.printStackTrace();
		}

		mnv.addObject("dataMap", dataMap);
		// mnv.addAllObjectes(dataMap);
		// 페이지 컨택스트 우어어

		mnv.setViewName(url);

		return mnv;
	}

	@RequestMapping(value = "/registForm", method = RequestMethod.GET)
	public void registForm() {
	}
	
	public String savePicture(String oldPicture, MultipartFile multi) throws Exception {
		
		String fileName = null;
		
		if (!(multi == null || multi.isEmpty() || multi.getSize() > 1024 * 1024 * 5)) {
			
			// 저장 폴더 설정
			String uploadPath = picturePath;
			fileName = MakeFileName.toUUIDFileName(multi.getOriginalFilename(), "$$");
			
			File storeFile = new File(uploadPath, fileName);
			
			storeFile.mkdirs();
			
			// local HDD에 저장
			multi.transferTo(storeFile);
			
			if (oldPicture != null && oldPicture.isEmpty()) {
				File oldFile = new File(uploadPath, oldPicture);
				if (oldFile.exists()) {
					oldFile.delete();
				}
			}
		}
		return fileName;
	}
	
	
	@RequestMapping(value = "/picture", method = RequestMethod.POST, produces = "text/plain;charset=utf-8")
	@ResponseBody
	  public ResponseEntity<String> getPicture(@RequestParam("pictureFile") MultipartFile multi, String oldPicture) throws Exception{
		
		ResponseEntity<String> entity = null;
		
		String result = "";
		HttpStatus status = null;
		
		// 파일 저장 확인
		if ((result = savePicture(oldPicture, multi)) == null) {
			result = "업로드를 실패했습니다.";
			status = HttpStatus.BAD_REQUEST;
		} else {
			status = HttpStatus.OK; 
		}
		
		entity = new ResponseEntity<String>(result, status);
		
		return entity; 
	  }

	@RequestMapping(value = "/getPicture", produces = "text/plain;charset=utf-8")
	@ResponseBody
	public ResponseEntity<byte[]> getPicture(String id) throws Exception {
		
		String fileName = memberService.getMember(id).getPicture();
		
		InputStream in = null;
		ResponseEntity<byte[]> entity = null;
		String imgPath = picturePath;
		
		try {
			in = new FileInputStream(new File(imgPath, fileName));
			entity = new ResponseEntity<byte[]>(IOUtils.toByteArray(in), HttpStatus.CREATED);
			
		} catch (IOException e) {
			entity = new ResponseEntity<byte[]>(HttpStatus.INTERNAL_SERVER_ERROR);
			
		} finally {
			in.close();
		}
		
		return entity;
	}

	@RequestMapping("/idCheck")
	@ResponseBody
	public ResponseEntity<String> idCheck(String id) throws Exception {
		ResponseEntity<String> entity = null;
		
		try {
			MemberVO member = memberService.getMember(id);
			
			if (member != null) {
				entity = new ResponseEntity<String>("duplicated", HttpStatus.OK);
			} else {
				entity = new ResponseEntity<String>("", HttpStatus.OK);
			}
			
		} catch (SQLException e) {
			entity = new ResponseEntity<String>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
		return entity;
	}
	
	@RequestMapping(value = "/regist", method = RequestMethod.POST)
	public String regist(MemberRegistCommand memberReq) throws Exception, IOException{
		String url = "member/regist_success";
		
		MemberVO member = memberReq.toMemberVO();
		
		memberService.regist(member);
		
		return url;
	}
	
	@RequestMapping(value = "/detail")
	public String detail(String id, Model model) throws Exception{
		String url = "member/detail";
		
		MemberVO member = memberService.getMember(id);
		model.addAttribute("member", member);
		
		return url;
	}
	
	@RequestMapping(value = "/modifyForm", method = RequestMethod.GET)
	public String modifyForm(String id, Model model) throws Exception{
		String url = "member/modifyForm";
		
		MemberVO member = memberService.getMember(id);
		
		String fileName = member.getPicture();
		
		fileName = MakeFileName.parseFileNameFromUUID(fileName, "\\$\\$");
		member.setPicture(fileName);
		
		model.addAttribute("member", member);
		
		return url;
	}
	
	@RequestMapping(value = "/modify")
	public String modify(MemberModifyCommand modifyReq, HttpSession session, RedirectAttributes rttr) throws Exception {
		String url = "redirect:/member/detail.do";
		
		MemberVO member = modifyReq.toMemberVO();
		
		// 신규파일 변경 및 기존 파일 삭제
		String oldPicture = memberService.getMember(member.getId()).getPicture();
		if (modifyReq.getUploadPicture() != null && !modifyReq.getUploadPicture().isEmpty()) {
			String fileName = savePicture(oldPicture, modifyReq.getPicture());
			member.setPicture(fileName);
		} else {
			member.setPicture(oldPicture);
		}
		
		// DB 내용 수정
		memberService.modify(member);
		
		// 로그인한 사용자의 경우 수정된 정보로  session 업로드
		rttr.addFlashAttribute("parentReload", false);
		MemberVO loginUser = (MemberVO) session.getAttribute("loginUser");
		if (loginUser != null && member.getId().equals(loginUser.getId())) {
			session.setAttribute("loginUser", member);
			rttr.addFlashAttribute("parentReload", true);
		}
		
		rttr.addAttribute("id", member.getId());
		rttr.addAttribute("from", "modify");
		rttr.addFlashAttribute("member", memberService.getMember(modifyReq.getId()));
		
		return url;
	}
	
	@RequestMapping(value = "/remove")
	public String remove(String id, HttpSession session, RedirectAttributes rttr) throws Exception{
		String url = "redirect:/member/detail.do";
		
		MemberVO member = null;
		
		member = memberService.getMember(id);
		
		String savePath = picturePath;
		
		// 이미지 파일 삭제
		File imageFile = new File(savePath, member.getPicture());
		if (imageFile.exists()) {
			imageFile.delete();
		}
		
		// DB 삭제
		memberService.remove(id);
		
		// 삭제되는 회원이 로그인 회원인 경우 로그아웃 해야함
		MemberVO loginUser = (MemberVO) session.getAttribute("loginUser");
		if (loginUser.getId().equals(member.getId())) {
			session.invalidate();
		}
		
		rttr.addFlashAttribute("removeMember", member);
		
		rttr.addAttribute("from", "remove");
		rttr.addAttribute("id", id);
		
		return url;
	}

}
