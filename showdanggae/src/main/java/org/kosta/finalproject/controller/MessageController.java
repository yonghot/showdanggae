package org.kosta.finalproject.controller;

import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

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

		return new ModelAndView("../WEB-INF/views/popup/message_popup","member_id",member_id);		
	}

	@RequestMapping("sendMessage.do")
	public ModelAndView sendMessage(HttpServletRequest request, MessageVO vo){	

		vo.setRead(0);
		messageService.sendMessage(vo);
		
		return  new ModelAndView("../WEB-INF/views/popup/message_ok","message","전송완료");
	}
	
	@RequestMapping("messagebox.do")
	public ModelAndView messagebox(String member_id){
		//사용자의 메세지함으로 이동
		List<MessageVO> list=messageService.myMessageBox(member_id);
		
		return new ModelAndView("message_MyMessageBox","list",list);	
	}
	

	@RequestMapping("messageBoxContent.do")
	public ModelAndView messageBoxContent(HttpServletRequest request, MessageVO vo) {

		MessageVO mvo = messageService.MyMessageShowPopUp(vo);
		// System.out.println("값이 잘 담기나?" + mvo);
		// return new
		// ModelAndView("../WEB-INF/views/popup/message_show","member_Id",member_Id);
		return new ModelAndView("../WEB-INF/views/popup/message_show", "mvo", mvo);
	}

	@RequestMapping("messageRead.do")
	public String messageRead(MessageVO vo){
	
		int mno=vo.getMno();
		messageService.messageRead(mno);
	
		return "../WEB-INF/views/popup/message_read";
	}


}
