package kr.or.ddit.command;

import com.jsp.dto.PdsVO;

public class PdsModifyCommand extends PdsRegistCommand{
	private String pno;
	
	private String[] deleteFile;
	
	public final String getPno() {
		return pno;
	}
	public final void setPno(String pno) {
		this.pno = pno;
	}
	public final String[] getDeleteFile() {
		return deleteFile;
	}
	public final void setDeleteFile(String[] deleteFile) {
		this.deleteFile = deleteFile;
	}
	
	@Override
	public PdsVO toPdsVO() {
		PdsVO pds = super.toPdsVO();
		pds.setPno(Integer.parseInt(pno));
		
		return pds;
	}
}
