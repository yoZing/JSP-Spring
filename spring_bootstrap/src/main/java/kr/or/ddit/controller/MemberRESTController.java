package kr.or.ddit.controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.SQLException;

import javax.annotation.Resource;

import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.jsp.dto.MemberVO;
import com.jsp.service.LoginSearchMemberService;
import com.jsp.util.MakeFileName;

@RestController
@RequestMapping("/member")
public class MemberRESTController {

	@Autowired
	private LoginSearchMemberService memberService;

	@Resource(name = "picturePath")
	private String picturePath;

	public String savePicture(String oldPicture, MultipartFile multi) throws Exception {

		String fileName = null;

		if (!(multi == null || multi.isEmpty() || multi.getSize() > 1024 * 1024 * 5)) {

			// 저장 폴더 설정
			String uploadPath = picturePath;
			fileName = MakeFileName.toUUIDFileName(multi.getOriginalFilename(), "$$");

			File storeFile = new File(uploadPath, fileName);

			storeFile.mkdirs();

			// local HDD에 저장
			multi.transferTo(storeFile);

			if (oldPicture != null && oldPicture.isEmpty()) {
				File oldFile = new File(uploadPath, oldPicture);
				if (oldFile.exists()) {
					oldFile.delete();
				}
			}
		}
		return fileName;
	}

	@RequestMapping(value = "/picture", method = RequestMethod.POST, produces = "text/plain;charset=utf-8")
	public ResponseEntity<String> getPicture(@RequestParam("pictureFile") MultipartFile multi, String oldPicture)
			throws Exception {

		ResponseEntity<String> entity = null;

		String result = "";
		HttpStatus status = null;

		// 파일 저장 확인
		if ((result = savePicture(oldPicture, multi)) == null) {
			result = "업로드를 실패했습니다.";
			status = HttpStatus.BAD_REQUEST;
		} else {
			status = HttpStatus.OK;
		}

		entity = new ResponseEntity<String>(result, status);

		return entity;
	}

	@RequestMapping(value = "/getPicture", produces = "text/plain;charset=utf-8")
	public ResponseEntity<byte[]> getPicture(String id) throws Exception {
		
		String fileName = memberService.getMember(id).getPicture();
		
		InputStream in = null;
		ResponseEntity<byte[]> entity = null;
		String imgPath = picturePath;
		
		try {
			in = new FileInputStream(new File(imgPath, fileName));
			entity = new ResponseEntity<byte[]>(IOUtils.toByteArray(in), HttpStatus.CREATED);
			
		} finally {
			in.close();
		}
		
		return entity;
	}

	@RequestMapping("/idCheck")
	public ResponseEntity<String> idCheck(String id) throws Exception {
		ResponseEntity<String> entity = null;

		MemberVO member = memberService.getMember(id);

		if (member != null) {
			entity = new ResponseEntity<String>("duplicated", HttpStatus.OK);
		} else {
			entity = new ResponseEntity<String>("", HttpStatus.OK);
		}

		return entity;
	}
}
