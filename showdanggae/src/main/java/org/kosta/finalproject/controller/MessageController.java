package org.kosta.finalproject.controller;

import java.util.HashMap;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.kosta.finalproject.model.member.MemberVO;
import org.kosta.finalproject.model.message.MessageListVO;
import org.kosta.finalproject.model.message.MessageService;
import org.kosta.finalproject.model.message.MessageVO;
import org.kosta.finalproject.model.message.MsPagingBean;
import org.kosta.finalproject.model.notice.ListVO;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class MessageController {

	@Resource
	private MessageService messageService;

	@RequestMapping("messagePopForm.do")
	public ModelAndView messagePopForm(HttpServletRequest request){		
		//관리자가 회원에게 보낼때
		String member_id=request.getParameter("member_id");
		return new ModelAndView("popup/message_popup","member_id",member_id);		
	}
	@RequestMapping("messagePopForm1.do")
	public ModelAndView messagePopForm1(HttpServletRequest request, MemberVO vo){		
	
		String member_id=request.getParameter("member_id");
		return new ModelAndView("../views/popup/message_popup1","member_id",member_id);		
	}

	@RequestMapping("sendMessage.do")
	public ModelAndView sendMessage(HttpServletRequest request, MessageVO vo){	
	
		vo.setRead(0);
		messageService.sendMessage(vo);
		return new ModelAndView("popup/message_ok");
	}
	
	@RequestMapping("auth_messagebox.do")
	public ModelAndView messagebox(String member_id,String pageNo){
		MessageListVO list= messageService.myMessageBox(member_id,pageNo);
		
		return new ModelAndView("message_MyMessageBox","mlist",list);	
	}
	

	@RequestMapping("auth_messageBoxContent.do")
	@ResponseBody
	public MessageVO messageBoxContent(MessageVO vo) {
		MessageVO msvo = messageService.MyMessageShowPopUp(vo);

		return msvo;
	}


}
