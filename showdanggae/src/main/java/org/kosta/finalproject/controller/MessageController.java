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


	/**
	 * 
	 * @Method Name  : sendMessage
	 * @작성일   : 2015. 12. 22. 
	 * @작성자   : 유서정
	 * @변경이력  :
	 * @Method 설명 :관리자가 특정회원에게 메세지를 보내줌
	 * @param request
	 * @param vo
	 * @return
	 */
	



/**
 * 
 * @Method 이름 : sendToMemberMessage
 * @Method 설명 : 아이디 검색, 팔로잉, 팔로워 탭 클릭시 나온 아이디가 있을 경우 아이디를 클릭하면 
 * 					 modal 메세지 창이 나오고 보내기 버튼을 눌렀을 경우 메세지가 보내진다.
 * @param request
 * @param vo
 * @return
 * @작성일 : 2015. 12. 22.
 * @작성자 : 송슬하,박준서
 */
	@RequestMapping("sendToMemberMessage.do")
	@ResponseBody
	public ModelAndView sendToMemberMessage(HttpServletRequest request, MessageVO vo){	
		System.out.println(vo);
		messageService.sendMessage(vo);
		return new ModelAndView("templates/main");
	}
	
	/**
	 * @Method Name  : messagebox
	 * @작성일   : 2015. 12. 22. 
	 * @작성자   : 유서정
	 * @변경이력  :
	 * @Method 설명 : 회원이 받은 메세지들을 리스트로 뽑아 보여준다
	 * 							메세지리스트 양이 한 페이지에 보여줄 수 있는 양보다 많을 수 있으므로
	 * 							pageNo를 받아 그 페이지에 해당하는 메세지 리스트를 보여준다
	 * @param member_id
	 * @param pageNo
	 * @return
	 */
	@RequestMapping("auth_messagebox.do")
	public ModelAndView messagebox(String member_id,String pageNo){
		MessageListVO list= messageService.myMessageBox(member_id,pageNo);
		
		return new ModelAndView("message_MyMessageBox","mlist",list);	
	}
	

	/**
	 * 
	 * @Method Name  : messageBoxContent
	 * @작성일   : 2015. 12. 22. 
	 * @작성자   : 유서정
	 * @변경이력  :
	 * @Method 설명 : 회원이 받은 메세지중 한 메세지를 클릭하면 그 메세지에 해당하는 내용과 정보를 보여준다
	 * @param vo
	 * @return
	 */
	@RequestMapping("auth_messageBoxContent.do")
	@ResponseBody
	public MessageVO messageBoxContent(MessageVO vo) {
		System.out.println(vo);
		MessageVO msvo = messageService.MyMessageShowPopUp(vo);

		return msvo;
	}


}
