package org.kosta.finalproject.controller;



import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.kosta.finalproject.model.notice.ListVO;
import org.kosta.finalproject.model.notice.NoticeService;
import org.kosta.finalproject.model.notice.NoticeVO;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class NoticeController {
	
	@Resource
	private NoticeService noticeService;
	
	
	@RequestMapping("notice.do")
	public ModelAndView notice(HttpServletRequest request,HttpServletResponse response){
		String pageNo=request.getParameter("pageNo");
		ListVO list=noticeService.noticeList(pageNo);
		
		return new ModelAndView("notice_noticeboardview","noticeList",list);
	}
	
	@RequestMapping("noticeShow.do")
	public ModelAndView noticeShow(HttpServletRequest request,HttpServletResponse response){
		//�Խõ� �� �����ֱ�
		String no2=request.getParameter("no"); //�۹�ȣ
		int no=Integer.parseInt(no2);

		NoticeVO vo=noticeService.noticeContent(no);
		
		return new ModelAndView("redirect:nohit.do?no=" + no);
	}
	
	@RequestMapping("nohit.do")
	public ModelAndView nohit(HttpServletRequest request,HttpServletResponse response){
		String no2=request.getParameter("no"); //�۹�ȣ
		int no=Integer.parseInt(no2);
		NoticeVO vo=noticeService.noHitnoticeContent(no);
		
		return new ModelAndView("notice_noticeshowview","content",vo);
	}
	
	@RequestMapping("noticeWriteForm.do")
	public String noticeWriteForm(){
		return "notice_noticeWrite";
		
	
	}
	
	
	@RequestMapping("noticeWritecancel.do")
	public String noticeWritecancel(){
		//�������׾��� ���
		return "home";		
	}
	
	@RequestMapping("write.do")
	public ModelAndView write(NoticeVO vo){
		//notice ���� �Է�
		noticeService.write(vo);
		return new ModelAndView("redirect:noticeShow.do?no=" +vo.getNo());
	}
	
	@RequestMapping("noticeUpdateForm.do")
	public ModelAndView noticeUpdateForm(String sno){
		//���������� �̵�
		int no=Integer.parseInt(sno);
		NoticeVO vo=noticeService.noticeContent(no);
		return new ModelAndView("notice_noticeUpdateForm","NoticeVO",vo);
	}
	
	@RequestMapping("update.do")
	public ModelAndView update(NoticeVO vo){
		//title�̶� content�� ��������
		NoticeVO updateVO=noticeService.noticeUpdate(vo);
		//������ �Ϸ� �������� �̵�
		//return new ModelAndView("notice_noticeshowview","content",vo);
		return new ModelAndView("notice_noticeshowview","content",updateVO);
	}
	
	@RequestMapping("notice_delete")
	public String notice_delete(HttpServletRequest request,HttpServletResponse response){
		
		String[] check_no=request.getParameterValues("check_no");	
		for(int i=0; i<check_no.length; i++){
			noticeService.noticeDelete(check_no[i]);
		}
		return "redirect:notice.do";
	}
	
	
	}




