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

	@RequestMapping("fileupload.do") //이게 중복되면 Controller 이름 앞에 소문자로 된 걸 못찾는다고 나옴
	@ResponseBody
	public String fileUpload(FileVO vo) {
		
		System.out.println("파일 업로드 컨트롤러 실행");
		System.out.println(path+" "+vo);
		
		MultipartFile imgFile = vo.getProImgFile();
		//업로드 파일이 없으면 파일명은 공란 처리된다
		String fileName = imgFile.getOriginalFilename();
		
		//업로드 파일이 있으면 파일을 특정 경로로 업로드(복사) 한다.
		if(!fileName.equals("")) {
			try {
				fileName=vo.getMember_id()+".jpg";
				imgFile.transferTo(new File(path+fileName));
				System.out.println(fileName + "업로드완료!");
				System.out.println(fileName);				
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		//return "redirect:Profile.do?member_id=" + member_id;
		return "redirect:profile.do?member_id=" + vo.getMember_id();
	}
		
}

