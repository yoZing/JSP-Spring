package com.jsp.action.pds;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.jsp.action.Action;
import com.jsp.dto.AttachVO;
import com.jsp.dto.PdsVO;
import com.jsp.service.PdsService;
import com.jsp.util.MakeFileName;

public class PdsModifyFormAction implements Action {

	public PdsService pdsService;

	public void setPdsServie(PdsService pdsService) {
		this.pdsService = pdsService;

	}

	@Override
	public String process(HttpServletRequest request, HttpServletResponse response)
			throws IOException, ServletException {
		String url = "/pds/modifyForm";

		try {
			int pno = Integer.parseInt(request.getParameter("pno"));

			PdsVO pds = pdsService.getPds(pno);			
			
			List<AttachVO> renamedAttachList = MakeFileName.parseFileNameFromAttaches(pds.getAttachList(), "\\$\\$");
			pds.setAttachList(renamedAttachList);

			request.setAttribute("pds", pds);
		} catch (Exception e) {
			e.printStackTrace();
			response.sendError(response.SC_INTERNAL_SERVER_ERROR);
			url = null;
		}
		return url;
	}

}
