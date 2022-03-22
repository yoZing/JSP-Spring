package kr.or.ddit.controller;

import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.jsp.command.Criteria;
import com.jsp.dto.BoardVO;
import com.jsp.service.BoardService;

@Controller
@RequestMapping("/board")
public class BoardController {

	@Resource(name = "boardService")
	private BoardService boardService;
	
	@RequestMapping("/main")
	public void main() throws Exception {};
	
	@RequestMapping(value = "/list")
	public ModelAndView list(Criteria cri, ModelAndView mnv) throws Exception {
		String url = "board/list";
		
		Map<String, Object> dataMap = boardService.getBoardList(cri);
		
		mnv.addObject("dataMap", dataMap);
		mnv.setViewName(url);
		
		return mnv;
	}
	
	@RequestMapping("/registForm")
	public String registForm() throws Exception{
		String url = "board/regist";
		
		return url;
	}
	
	@RequestMapping("/regist")
	public String regist(BoardVO board, HttpServletRequest request, RedirectAttributes rttr) throws Exception {
		String url = "redirect:/board/list.do";
		
		board.setTitle((String) request.getAttribute("XSStitle"));
		
		boardService.regist(board);
		
		rttr.addFlashAttribute("from", "regist");
		
		return url;
	}
	
	@RequestMapping("/detail")
	public ModelAndView detail(int bno, String from, ModelAndView mnv) throws Exception {
		String url = "board/detail";
		
		BoardVO board = null;
		
		if (from != null && from.equals("list")) {
			board = boardService.getBoard(bno);
			url = "redirect:/board/detail.do?bno=" + bno;
		} else {
			board = boardService.getBoardForModify(bno);
			
		}
		mnv.addObject("board", board);
		mnv.setViewName(url);
		
		return mnv;
	}
	
	@RequestMapping("/modifyForm")
	public ModelAndView modifyForm(int bno, ModelAndView mnv) throws Exception {
		String url = "board/modifyForm";
		
		BoardVO board = boardService.getBoardForModify(bno);
		
		mnv.addObject("board", board);
		mnv.setViewName(url);
		
		return mnv;
	}
	
	@RequestMapping(value = "/modify", method = RequestMethod.POST)
	public String modify(BoardVO board, HttpServletRequest request, RedirectAttributes rttr) throws Exception {
		String url = "redirect:/board/detail.do";
		
		board.setTitle((String) request.getAttribute("XSStitle"));
		
		boardService.modify(board);
		
		rttr.addFlashAttribute("from", "modify");
		rttr.addAttribute("bno", board.getBno());
		
		return url;
	}
		
	@RequestMapping("/remove")
	public String remove(int bno, RedirectAttributes rttr) throws Exception {
		String url = "redirect:/board/detail.do";
		
		boardService.remove(bno);
		
		rttr.addAttribute("bno", bno);	// detail로 돌리면 이거 없으면 터진다
		rttr.addFlashAttribute("from", "remove");
		
		return url;
	}
	
	
}
