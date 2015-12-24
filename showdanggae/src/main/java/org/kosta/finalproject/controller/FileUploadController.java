package org.kosta.finalproject.controller;

import java.io.File;
import javax.annotation.Resource;

import org.kosta.finalproject.model.member.FileVO;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class FileUploadController {
	
	@Resource(name="uploadPath")
	private String path;

	
	/**
	 * 
	 * @Method Name  : fileUpload
	 * @작성일   : 2015. 12. 22. 
	 * @작성자   : 유서정
	 * @변경이력  :
	 * @Method 설명 :회원이 선택한 프로필 사진을 업로드 해준다
	 * @param vo
	 * @return
	 */
	@RequestMapping("fileupload.do") 
	@ResponseBody
	public ModelAndView fileUpload(FileVO vo) {
		
		MultipartFile imgFile = vo.getProImgFile();
		//업로드 파일이 없으면 파일명은 공란 처리된다
		String fileName = imgFile.getOriginalFilename();
		
		//업로드 파일이 있으면 파일을 특정 경로로 업로드(복사) 한다.
		if(!fileName.equals("")) {
			try {
				fileName=vo.getMember_id()+".jpg";
				imgFile.transferTo(new File(path+fileName));
			
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		
		return new ModelAndView("member_profile");
	}
		
}

