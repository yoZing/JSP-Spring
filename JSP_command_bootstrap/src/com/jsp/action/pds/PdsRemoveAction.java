package com.jsp.action.pds;

import java.io.File;
import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.jsp.action.Action;
import com.jsp.dto.AttachVO;
import com.jsp.service.PdsService;

public class PdsRemoveAction implements Action {

	private PdsService pdsService;

	public void setPdsService(PdsService pdsService) {
		this.pdsService = pdsService;
	}

	@Override
	public String process(HttpServletRequest request, HttpServletResponse response)
			throws IOException, ServletException {
		String url = "pds/remove_success";

		try {
			int pno = Integer.parseInt(request.getParameter("pno"));

			// 파일 삭제
			// pno에 대한 attachList 확보
			List<AttachVO> attachList = pdsService.getPds(pno).getAttachList();
			if (attachList != null) {
				for (AttachVO attach : attachList) {
					String storedFilePath = attach.getUploadPath() + File.separator + attach.getFileName();
					File file = new File(storedFilePath);
					if (file.exists()) {
						file.delete(); // 파일 삭제
					}
				}
			}

			// DB 내용 삭제
			pdsService.remove(pno);

		} catch (Exception e) {
			response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
			url = null;
			e.printStackTrace();
		}
		return url;
	}

}
