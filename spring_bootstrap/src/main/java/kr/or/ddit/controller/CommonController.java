package kr.or.ddit.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.jsp.dto.MenuVO;
import com.jsp.exception.IdNotFoundException;
import com.jsp.exception.InvalidPasswordException;
import com.jsp.service.LoginSearchMemberService;
import com.jsp.service.MenuService;

@Controller
public class CommonController {

	@Autowired
	private MenuService menuService;
	
	@Autowired
	private LoginSearchMemberService memberService;
	
//	@Resource(name = "memberService")
//	LoginSeachMemberService loginSeachMemberService;
	
	@RequestMapping(value = "/index", method = RequestMethod.GET)
	public String index(@RequestParam(defaultValue = "M000000") String mCode, Model model) throws Exception {
		String url = "common/indexPage";
		
		List<MenuVO> menuList = menuService.getMainMenuList();
		MenuVO menu = menuService.getMenuByMcode(mCode);
		
		model.addAttribute("menuList", menuList);
		model.addAttribute("menu", menu);
		
		return url;
	}
	
	@RequestMapping(value = "/main", method = RequestMethod.GET)
	public void main() throws Exception {}
	
	@RequestMapping(value = "/subMenu", method = RequestMethod.GET)
	public void subMenu(String mCode) throws Exception {
		menuService.getSubMenuList(mCode);
		
		
	}
	
	@RequestMapping(value = "/common/loginForm", method = RequestMethod.GET)
	public void loginForm() {}

	@RequestMapping(value = "/common/login", method = RequestMethod.POST)
	public String login(HttpServletRequest req, String id, String pwd, Model model) throws Exception {
		String url = "redirect:/index.do";
		
		try {
			memberService.login(id, pwd);
			
			HttpSession session = req.getSession();
			session.setAttribute("loginUser", memberService.getMember(id));
			session.setMaxInactiveInterval(6 * 60 * 10);
			
		} catch (IdNotFoundException | InvalidPasswordException e) {
			model.addAttribute("message", e.getMessage());
			url = "common/login_fail";
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}

		return url;
	}
	
	@RequestMapping(value = "/common/logout", method = RequestMethod.GET)
	public String logout(HttpServletRequest req) throws Exception {
		String url = "redirect:/";
		
		HttpSession session = req.getSession();
		session.invalidate();
		
		return url;
	}
	
}
