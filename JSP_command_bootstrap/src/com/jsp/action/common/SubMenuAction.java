package com.jsp.action.common;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.jsp.action.Action;
import com.jsp.controller.JSONViewResolver;
import com.jsp.dto.MenuVO;
import com.jsp.service.MenuService;

public class SubMenuAction implements Action {

	private MenuService menuService;
	public void setMenuService(MenuService menuService) {
		this.menuService = menuService;
	}
	
	@Override
	public String process(HttpServletRequest req, HttpServletResponse resp) throws Exception {
		String url = null;
		
		String mCode = req.getParameter("mCode");
		List<MenuVO> subMenu = null;
		
		try {
			subMenu = menuService.getSubMenuList(mCode);
			
			JSONViewResolver.view(resp, subMenu);
			
		} catch (Exception e) {
			e.printStackTrace();
			// Exception 처리.....
			throw e;
		}
		
		return url;
	}
}
