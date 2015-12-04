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
	MessageService messageService;

	@RequestMapping("messagePopForm.do")
	public ModelAndView messagePopForm(HttpServletRequest request) {
		// 전달받은 아이디값
		String member_Id = request.getParameter("member_Id");
		System.out.println("전달" + member_Id);
		return new ModelAndView("../views/popup/message_popup", "member_Id",
				member_Id);
	}

	@RequestMapping("sendMessage.do")
	public void sendMessage(MessageVO vo) {
		// System.out.println(vo); spand_date 널값
		System.out.println(vo);
		messageService.sendMessage(vo);
	}

	@RequestMapping("messagebox.do")
	public ModelAndView messagebox(HttpServletRequest request) {
		// 사용자의 메세지함으로 이동
		String member_id = request.getParameter("member_id");
		List<MessageVO> list = messageService.myMessageBox(member_id);
		return new ModelAndView("message_MyMessageBox", "list", list);
	}

	@RequestMapping("messageBoxContent.do")
	public ModelAndView messageBoxContent(HttpServletRequest request,
			MessageVO vo) {

		MessageVO mvo = messageService.MyMessageShowPopUp(vo);
		// System.out.println("값이 잘 담기나?" + mvo);
		// return new
		// ModelAndView("../WEB-INF/views/popup/message_show","member_Id",member_Id);
		return new ModelAndView("../WEB-INF/views/popup/message_show", "mvo",
				mvo);
	}

	@RequestMapping("messageRead.do")
	public void messageRead(MessageVO vo) {
		String mno = vo.getMno();
		messageService.messageRead(mno);
		// return "redirect:messagebox.do"; 새로고침이 필요함
	}

}
