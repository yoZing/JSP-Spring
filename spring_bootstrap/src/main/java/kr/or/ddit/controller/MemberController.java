package kr.or.ddit.controller;

import java.io.File;
import java.io.IOException;
import java.sql.SQLException;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
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
	
	@Autowired
	private ExceptionLoggerHelper exceptionLogger;
	
	@RequestMapping("/main")
	public String main() {
		String url = "member/main.open";
		
		return url;
	}
	
	@RequestMapping("/list")
	public ModelAndView list(Criteria cri,HttpServletRequest request,							
							 ModelAndView mnv) throws SQLException {
		String url = "member/list.open";
		
		Map<String, Object> dataMap=null;
		
		try {
			
			dataMap = memberService.getSearchMemberList(cri);
		} catch (SQLException e) { 
			exceptionLogger.write(request, e, "MemberService");
			throw e;
			
		} catch(Exception e) {
			e.printStackTrace();			
		}
		
		mnv.addObject("dataMap",dataMap);
		//mnv.addAllObjects(dataMap);		
		mnv.setViewName(url);	
		
		return mnv;
	}
	
	@RequestMapping(value = "/registForm", method = RequestMethod.GET)
	public String registForm() {
		String url = "member/regist.open";
		return url;
	}
	
	
	@RequestMapping(value = "/regist", method = RequestMethod.POST)
	public String regist(MemberRegistCommand memberReq) throws Exception, IOException {
		String url = "member/regist_success";

		MemberVO member = memberReq.toMemberVO();
		memberService.regist(member);

		return url;
	}
	
	@RequestMapping(value = "/detail", method = RequestMethod.GET)
	public String detail(String id, Model model) throws Exception {

		String url = "member/detail.open";

		MemberVO member = memberService.getMember(id);
		model.addAttribute("member", member);
		

		return url;
	}
	
	@RequestMapping(value = "/modifyForm", method = RequestMethod.GET)
	public String modifyForm(String id, Model model)throws Exception {

		String url = "member/modify.open";

		MemberVO member = memberService.getMember(id);
		
		String picture = MakeFileName.parseFileNameFromUUID(member.getPicture(), "\\$\\$");
		member.setPicture(picture);
		
		model.addAttribute("member", member);
		

		return url;
	}
	
	@RequestMapping(value = "/modify", method = RequestMethod.POST)
	public String modify(MemberModifyCommand modifyReq,HttpSession session,RedirectAttributes rttr)
																					throws Exception {
		String url="redirect:/member/detail.do";
		
		MemberVO member = modifyReq.toMember();
		
		// 신규 파일 변경 및 기존 파일 삭제
		String oldPicture = memberService.getMember(member.getId()).getPicture();
		if(modifyReq.getUploadPicture()!=null && !modifyReq.getUploadPicture().isEmpty()) {			
			String fileName = savePicture(oldPicture, modifyReq.getPicture());
			member.setPicture(fileName);
		}else {
			member.setPicture(oldPicture);			
		}
		
		//DB 내용 수정
		memberService.modify(member);		
		
		// 로그인한 사용자의 경우 수정된 정보로 session 업로드
		rttr.addFlashAttribute("parentReload",false);
		MemberVO loginUser = (MemberVO) session.getAttribute("loginUser");
		if (loginUser != null && member.getId().equals(loginUser.getId())) {
			session.setAttribute("loginUser", member);
			rttr.addFlashAttribute("parentReload",true);
		}
		
		
		rttr.addAttribute("id",member.getId());
		rttr.addAttribute("from","modify");
		rttr.addFlashAttribute("member",memberService.getMember(modifyReq.getId()));
		return url;
	}
	
	@Resource(name = "picturePath")
	private String picturePath;
	
	@RequestMapping(value = "/remove", method = RequestMethod.GET)
	public String remove(String id, HttpSession session, RedirectAttributes rttr) throws Exception {
		String url = "redirect:/member/detail.do";
		
		MemberVO member;
		
		// 이미지 파일을 삭제
		member = memberService.getMember(id);
		String savePath = this.picturePath;
		File imageFile = new File(savePath, member.getPicture());
		if (imageFile.exists()) {
			imageFile.delete();
		}
		
		//DB삭제
		memberService.remove(id);
		
		// 삭제되는 회원이 로그인 회원인경우 로그아웃 해야함.
		MemberVO loginUser = (MemberVO) session.getAttribute("loginUser");
		if (loginUser.getId().equals(member.getId())) {
			session.invalidate();
		}
		
		rttr.addFlashAttribute("removeMember",member);
		
		rttr.addAttribute("from","remove");		
		rttr.addAttribute("id",id);
		
		return url;
	}
	
	private String savePicture(String oldPicture, MultipartFile multi) throws Exception {
		String fileName = null;

		/* 파일유무확인 */
		if (!(multi == null || multi.isEmpty() || multi.getSize() > 1024 * 1024 * 5)) {

			/* 파일저장폴더설정 */
			String uploadPath = picturePath;
			fileName = MakeFileName.toUUIDFileName(multi.getOriginalFilename(), "$$");
			File storeFile = new File(uploadPath, fileName);

			storeFile.mkdirs();

			// local HDD 에 저장.
			multi.transferTo(storeFile);

			if (oldPicture != null && !oldPicture.isEmpty()) {
				File oldFile = new File(uploadPath, oldPicture);
				if (oldFile.exists()) {
					oldFile.delete();
				}
			}
		}
		return fileName;
	}
}