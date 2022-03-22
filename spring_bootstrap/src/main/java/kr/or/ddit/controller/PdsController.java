package kr.or.ddit.controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.josephoconnell.html.HTMLInputFilter;
import com.jsp.command.Criteria;
import com.jsp.dto.AttachVO;
import com.jsp.dto.PdsVO;
import com.jsp.service.PdsService;
import com.jsp.util.GetUploadPath;

import kr.or.ddit.command.PdsModifyCommand;
import kr.or.ddit.command.PdsRegistCommand;
import kr.or.ddit.util.GetAttachesByMultipartFileAdapter;

@Controller
@RequestMapping("/pds")
public class PdsController {
	@Autowired
	private PdsService pdsService;
	
	@Resource(name="fileUploadPath")
	private String fileUploadPath;
	
	
	@RequestMapping("/main")
	public void main() throws Exception {}
	
	@RequestMapping("/list")
	public ModelAndView list(Criteria cri, ModelAndView mnv) throws Exception {
		String url = "pds/list";
		
		Map<String, Object> dataMap = pdsService.getList(cri);;
		
		mnv.addObject("dataMap", dataMap);
		mnv.setViewName(url);
		
		return mnv;
	}
	
	@RequestMapping("/registForm")
	public String regist() {
		String url = "pds/registForm";
		
		return url;
	}
	
	@RequestMapping(value = "/regist", method = RequestMethod.POST, produces="text/plain;charset=utf-8")
	public String regist(PdsRegistCommand registReq, HttpServletRequest request, RedirectAttributes rttr) throws Exception {
		String url = "redirect:/pds/list.do";
		
		String savePath = fileUploadPath;
		
		List<AttachVO> attachList = GetAttachesByMultipartFileAdapter.save(registReq.getUploadFile(), savePath);
		
		PdsVO pds = registReq.toPdsVO();
		
		pds.setAttachList(attachList);
		pds.setTitle((String) request.getAttribute("XSStitle"));
		
		pdsService.regist(pds);
		
		rttr.addFlashAttribute("from", "regist");
		
		return url;
	}
	
	@RequestMapping(value = "/detail", method = RequestMethod.GET)
	public ModelAndView detail(int pno, String from, ModelAndView mnv) throws Exception {
		String url = "pds/detail";
		
		PdsVO pds = null;
		
		if (from != null && from.equals("list")) {
			pds = pdsService.read(pno);
			url = "redirect:/pds/detail.do?pno=" + pno;
		} else {
			pds = pdsService.getPds(pno);

		}
		
		// 파일명 재정의
		if (pds != null) {
			List<AttachVO> attachList = pds.getAttachList();
			if (attachList != null) {
				for (AttachVO attach : attachList) {
					// attach.setFileName(MakeFileName.parseFileNameFromUUID(attach.getFileName(), "\\$\\$"));
					String fileName = attach.getFileName().split("\\$\\$")[1];
					attach.setFileName(fileName);
				}
			}
		}
		
		mnv.addObject("pds", pds);
		mnv.setViewName(url);
		
		return mnv;
	}
	
	@RequestMapping(value = "/getFile", produces = "test/plain;charset=utf-8")
	@ResponseBody
	public ResponseEntity<byte[]> getFile(int ano) throws Exception {
		ResponseEntity<byte[]> entity = null;
		
		AttachVO attach = pdsService.getAttachByAno(ano);
		
		String fileName = attach.getFileName();
		String uploadPath = attach.getUploadPath();
		
		File file = new File(uploadPath, fileName);
		
		InputStream in = null;
		
		try {
			in = new FileInputStream(file);
			entity = new ResponseEntity<byte[]>(IOUtils.toByteArray(in), HttpStatus.CREATED);
		} catch (Exception e) {
			e.printStackTrace();
			entity = new ResponseEntity<byte[]>(HttpStatus.INTERNAL_SERVER_ERROR);
		} finally {
			in.close();
		}
		
		return entity;
	}
	
	
	@RequestMapping("/modifyForm")
	public ModelAndView modifyForm(int pno, ModelAndView mnv) throws Exception {
		String url = "pds/modifyForm";
		
		PdsVO pds = pdsService.getPds(pno);
		
		// 파일명 재정의
		if (pds != null) {
			List<AttachVO> attachList = pds.getAttachList();
			if (attachList != null) {
				for (AttachVO attach : attachList) {
					// attach.setFileName(MakeFileName.parseFileNameFromUUID(attach.getFileName(), "\\$\\$"));
					String fileName = attach.getFileName().split("\\$\\$")[1];
					attach.setFileName(fileName);
				}
			}
		}
		
		mnv.addObject("pds", pds);
		mnv.setViewName(url);
		
		return mnv;
	}
	
	@RequestMapping("/modify")
	public String modify(PdsModifyCommand modifyReq, HttpServletRequest request, RedirectAttributes rttr) throws Exception {
		String url = "redirect:/pds/detail.do";
		
		// 파일 삭제
		if (modifyReq.getDeleteFile() != null && modifyReq.getDeleteFile().length > 0) {
			for (String anoStr : modifyReq.getDeleteFile()) {
				int ano = Integer.parseInt(anoStr);
				AttachVO attach = pdsService.getAttachByAno(ano);
				
				File deleteFile = new File(fileUploadPath, attach.getFileName());
				
				if (deleteFile.exists()) {
					deleteFile.delete();	// File 삭제
				}
				pdsService.removeAttachByAno(ano);	// DB 삭제
			}
		}
		// 파일 저장
		List<AttachVO> attachList = GetAttachesByMultipartFileAdapter.save(modifyReq.getUploadFile(), fileUploadPath);
		
		// PdsVO setting
		PdsVO pds = modifyReq.toPdsVO();
		
		pds.setAttachList(attachList);
		pds.setTitle(HTMLInputFilter.htmlSpecialChars(pds.getTitle()));
		
		// DB 저장
		pdsService.modify(pds);
		
		rttr.addAttribute("pno", pds.getPno());
		rttr.addFlashAttribute("from", "modify");
		
		return url;
	}
	
	@RequestMapping("/remove")
	public String remove(int pno, RedirectAttributes rttr) throws Exception {
		String url = "redirect:/pds/detail.do";
		
		// 파일 삭제
		List<AttachVO> attachList = pdsService.getPds(pno).getAttachList();
		
		if (attachList != null) {
			for (AttachVO attach : attachList) {
				File target = new File(attach.getUploadPath(), attach.getFileName());
				if (target.exists()) {
					target.delete();
				}
			}
		}
		
		// DB 처리
		pdsService.remove(pno);
		
		rttr.addAttribute("pno", pno);
		rttr.addFlashAttribute("from", "remove");
		
		return url;
	}
}
