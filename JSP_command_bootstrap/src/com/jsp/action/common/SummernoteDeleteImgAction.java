package com.jsp.action.common;

import java.io.File;
import java.io.PrintWriter;
import java.net.URLDecoder;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.jsp.action.Action;
import com.jsp.command.SummernoteDeleteImgCommand;
import com.jsp.util.GetUploadPath;

public class SummernoteDeleteImgAction implements Action {

	@Override
	public String process(HttpServletRequest req, HttpServletResponse resp) throws Exception {
		String url = null;
		
		ObjectMapper mapper = new ObjectMapper();
		
		SummernoteDeleteImgCommand delReq = mapper.readValue(req.getReader(), SummernoteDeleteImgCommand.class);
		
		String savePath = GetUploadPath.getUploadPath("summernote.img");
		String fileName = URLDecoder.decode(delReq.getFileName(), "utf-8");
		
		File target = new File(savePath + File.separator + fileName);
		
		if (!target.exists()) {
			resp.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
		} else {
			target.delete();
			
			resp.setContentType("text/plain;charset=utf-8");
			PrintWriter out = resp.getWriter();
			out.print(fileName + " 이미지를 삭제했습니다.");
		}
		
		return url;
	}
}
