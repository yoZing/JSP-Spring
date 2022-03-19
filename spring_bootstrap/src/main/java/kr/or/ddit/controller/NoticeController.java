package kr.or.ddit.controller;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.jsp.command.Criteria;
import com.jsp.dto.NoticeVO;
import com.jsp.service.NoticeService;

@Controller
@RequestMapping("notice")
public class NoticeController {
	
	@Autowired
	private NoticeService noticeService;
	
	@RequestMapping("/main")
	public void main() {}
	
	@RequestMapping("/list")
	public void list(Criteria cri, Model model) throws Exception {
		
		Map<String, Object> dataMap = noticeService.getNoticeList(cri);
		model.addAttribute("dataMap", dataMap);
	}
	
	@RequestMapping("/registForm")
	public String registForm() {
		String url = "notice/registForm";
		
		return url;
	}
	
	@RequestMapping("/regist")
	public String regist(NoticeVO notice, HttpServletRequest request, RedirectAttributes rttr) throws Exception {
		String url = "redirect:/notice/list";
		
		notice.setTitle( (String) request.getAttribute("XSStitle"));
		
		noticeService.regist(notice);
		
		rttr.addFlashAttribute("from", "regist");
		
		return url;
	}
	
	@RequestMapping("/detail")
	public ModelAndView detail(int nno, @RequestParam(defaultValue = "") String from, HttpServletRequest request, ModelAndView mnv) throws Exception{
		String url = "notice/detail";
		
		NoticeVO notice = null;
		
		if (!from.equals("list")) {
			notice = noticeService.getNoticeForModify(nno);
		} else {
			notice = noticeService.getNotice(nno);
			url = "redirect:/notice/detail.do?nno=" + nno;
		}
		
		mnv.addObject("notice", notice);
		mnv.setViewName(url);
		
		return mnv;
	}
	
	@RequestMapping("/modifyForm")
	public ModelAndView modifyForm(int nno, ModelAndView mnv) throws Exception {
		String url = "notice/modify";
		
		NoticeVO notice = noticeService.getNoticeForModify(nno);
		
		mnv.addObject("notice", notice);
		mnv.setViewName(url);
		
		return mnv;
	}
	
	@RequestMapping("/modify")
	public 
	
}
