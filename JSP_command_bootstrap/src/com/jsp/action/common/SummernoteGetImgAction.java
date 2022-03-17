package com.jsp.action.common;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.jsp.action.Action;
import com.jsp.controller.FileDownloadResolver;
import com.jsp.util.GetUploadPath;

public class SummernoteGetImgAction implements Action {

	@Override
	public String process(HttpServletRequest req, HttpServletResponse resp) throws Exception {
		String url = null;
		
		// 파일명
		String fileName = req.getParameter("fileName");
		
		// 실제 저장 경로를 설정.
		String savePath = GetUploadPath.getUploadPath("summernote.img");
		
		FileDownloadResolver.sendFile(fileName, savePath, req, resp);

		return url;
	}
}
