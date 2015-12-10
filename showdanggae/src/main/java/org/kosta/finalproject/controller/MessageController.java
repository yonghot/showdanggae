package org.kosta.finalproject.controller;

import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.kosta.finalproject.model.member.MemberVO;
import org.kosta.finalproject.model.message.MessageService;
import org.kosta.finalproject.model.message.MessageVO;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class MessageController {

	@Resource
	private MessageService messageService;

	@RequestMapping("messagePopForm.do")
	public ModelAndView messagePopForm(HttpServletRequest request){		
		//전달받은 아이디값
		String member_id=request.getParameter("member_id");
		return new ModelAndView("popup/message_popup","member_id",member_id);		
	}
	
	@RequestMapping("messagePopForm1.do")
	public ModelAndView messagePopForm1(HttpServletRequest request, MemberVO vo){		
		//전달받은 아이디값
		//String member_id=request.getParameter("member_id");
		String member_id=request.getParameter("member_id");
		return new ModelAndView("../views/popup/message_popup1","member_id",member_id);		
	}

	@RequestMapping("sendMessage.do")
	public ModelAndView sendMessage(HttpServletRequest request, MessageVO vo){	
		vo.setRead(0);
		messageService.sendMessage(vo);	
		return  new ModelAndView("popup/message_ok","message","전송완료");
	}
	
	@RequestMapping("auth_messagebox.do")
	public ModelAndView messagebox(String member_id){
		//사용자의 메세지함으로 이동
		List<MessageVO> list=messageService.myMessageBox(member_id);
		
		return new ModelAndView("message_MyMessageBox","list",list);	
	}
	

	@RequestMapping("messageBoxContent.do")
	public ModelAndView messageBoxContent(HttpServletRequest request, MessageVO vo) {

		MessageVO mvo = messageService.MyMessageShowPopUp(vo);
		return new ModelAndView("popup/message_show", "mvo", mvo);
	}

	@RequestMapping("messageRead.do")
	public String messageRead(MessageVO vo){
	
		int mno=vo.getMno();
		messageService.messageRead(mno);
	
		return "popup/message_read";
	}

}
