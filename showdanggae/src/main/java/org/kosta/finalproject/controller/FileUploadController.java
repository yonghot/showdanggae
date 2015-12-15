package org.kosta.finalproject.controller;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.annotation.Resource;

import org.kosta.finalproject.model.product.FileVO;
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
	public void fileUpload(FileVO vo) {
		
		System.out.println("파일 업로드 컨트롤러 실행");
		
		System.out.println(path+" "+vo.getUserInfo());
		
		List<MultipartFile> list = vo.getFile();
		
		//view 화면에 업로드 된 파일 목록을 전달하기 위한 리스트
		ArrayList<String> nameList = new ArrayList<String>();
		
		for(int i=0;i<list.size();i++) {
			//업로드 파일이 없으면 파일명은 공란 처리된다
			String fileName = list.get(i).getOriginalFilename();
			
			//업로드 파일이 있으면 파일을 특정 경로로 업로드(복사) 한다.
			if(!fileName.equals("")) {
				try {
					list.get(i).transferTo(new File(path+fileName));
					
					System.out.println(fileName);
					
					nameList.add(fileName);
					
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}
	}
	
	/**
	 * BeanNameViewResolver 방식으로 downloadView를 실행시킨다.
	 * 
	 * @param filename
	 * @return
	 */
	/*@RequestMapping("fileDownload.do")
	public ModelAndView fileDownload(String filename) {
		
		HashMap<String, String> map = new HashMap<String, String>();
		
		map.put("path", path);
		
		return new ModelAndView("downloadView", map);
	}*/
	
}













