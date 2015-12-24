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

	/**
	 * @Method Name  : notice
	 * @작성일   : 2015. 12. 22. 
	 * @작성자   : 유서정
	 * @변경이력  :
	 * @Method 설명 : 관리자가 쓴 공지사항들을  list로 보여준다
	 * 							한 페이지당 보여지는 리스트 갯수를 초과할 수 있으므로
	 * 						 	page를 변수로 받아 그에 해당하는 list 결과를 보여준다
	 * @param pageNo
	 * @return
	 */
	@RequestMapping("notice.do")
	public ModelAndView notice(String pageNo) {
		ListVO list = noticeService.noticeList(pageNo);
		return new ModelAndView("notice_noticeboardview", "noticeList", list);
	}

	/**
	 * 
	 * @Method Name  : noticeShow
	 * @작성일   : 2015. 12. 22. 
	 * @작성자   : 유서정
	 * @변경이력  :
	 * @Method 설명 : 공지사항 list중 하나의 공지사항을 선택하면 선택된 공지사항의 내용을 보여준다
	 * @param no
	 * @return
	 */
	@RequestMapping("noticeShow.do")
	public ModelAndView noticeShow(String no) {
		int noInt = Integer.parseInt(no);
		noticeService.noticeContent(noInt);
		return new ModelAndView("redirect:nohit.do?no="+noInt);
	}

	/**
	 * 
	 * @Method Name  : nohit
	 * @작성일   : 2015. 12. 22. 
	 * @작성자   : 유서정
	 * @변경이력  :
	 * @Method 설명 : 회원들이 선택한 공지사항 글의 조회수를 올려준다
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping("nohit.do")
	public ModelAndView nohit(HttpServletRequest request,
			HttpServletResponse response) {
		String no2 = request.getParameter("no"); 
		int no = Integer.parseInt(no2);
		NoticeVO vo = noticeService.noHitnoticeContent(no);
		System.out.println("nohit"  +vo);
		return new ModelAndView("notice_noticeshowview", "content", vo);
	}

	/**
	 * 
	 * @Method Name  : noticeWriteForm
	 * @작성일   : 2015. 12. 22. 
	 * @작성자   : 유서정
	 * @변경이력  :
	 * @Method 설명 : 관리자가 공지사항글을 쓸 수 있는 View로 이동시킨다
	 * @return
	 */
	@RequestMapping("noticeWriteForm.do")
	public String noticeWriteForm() {
		return "notice_noticeWrite";

	}

	/**
	 * 
	 * @Method Name  : noticeWritecancel
	 * @작성일   : 2015. 12. 22. 
	 * @작성자   : 유서정
	 * @변경이력  :
	 * @Method 설명 : 관리자가 공지사항을 작성시 취소버튼을 누르면 home으로 이동시킨다
	 * @return
	 */
	@RequestMapping("noticeWritecancel.do")
	public String noticeWritecancel() {
		return "home";
	}

	/**
	 * 
	 * @Method Name  : write
	 * @작성일   : 2015. 12. 22. 
	 * @작성자   : 유서정
	 * @변경이력  :
	 * @Method 설명 : 관리자가 공지사항을 작성한 정보를 DB에 저장시키고 저장된 내용을 다시 보여준다
	 * @param vo
	 * @return
	 */
	@RequestMapping("write.do")
	public ModelAndView write(NoticeVO vo) {
		noticeService.write(vo);
		return new ModelAndView("redirect:noticeShow.do?no=" + vo.getNo());
	}

	/**
	 * 
	 * @Method Name  : noticeUpdateForm
	 * @작성일   : 2015. 12. 22. 
	 * @작성자   : 유서정
	 * @변경이력  :
	 * @Method 설명 :관리자가 자신이 썼던 공지사항을 수정할 수 있도록 공지사항 수정 view로 이동시킨다
	 * @param sno
	 * @return
	 */
	@RequestMapping("noticeUpdateForm.do")
	public ModelAndView noticeUpdateForm(String sno) {
		int no = Integer.parseInt(sno);
		NoticeVO vo = noticeService.noticeContent(no);
		return new ModelAndView("notice_noticeUpdateForm", "NoticeVO", vo);
	}
	
	/**
	 * 
	 * @Method Name  : noticeDeleteForm
	 * @작성일   : 2015. 12. 22. 
	 * @작성자   : 유서정
	 * @변경이력  :
	 * @Method 설명 : 관리자가 선택한 공지사항글을 삭제한다
	 * @param sno
	 * @return
	 */
	@RequestMapping("noticeDeleteForm.do")
	public String noticeDeleteForm(String sno) {
		int no = Integer.parseInt(sno);
		noticeService.deleteContent(no);
		return "redirect:notice.do";
		
		
	}

	/**
	 * 
	 * @Method Name  : update
	 * @작성일   : 2015. 12. 22. 
	 * @작성자   : 유서정
	 * @변경이력  :
	 * @Method 설명 : 관리자가 쓴 공지사항을 수정한 내용을 db에 저장시킨다
	 * @param vo
	 * @return
	 */
	@RequestMapping("update.do")
	public ModelAndView update(NoticeVO vo) {
		NoticeVO updateVO = noticeService.noticeUpdate(vo);
		// return new ModelAndView("notice_noticeshowview","content",vo);
		return new ModelAndView("notice_noticeshowview", "content", updateVO);
	}

	/**
	 * 
	 * @Method Name  : notice_delete
	 * @작성일   : 2015. 12. 22. 
	 * @작성자   : 유서정
	 * @변경이력  :
	 * @Method 설명 : 관리자가 선택한 다수의 공지사항 번호들을 배열로 받아 삭제시킨다
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping("notice_delete.do")
		public String notice_delete(HttpServletRequest request, HttpServletResponse response) {
			String[] check_no = request.getParameterValues("check_no");
			for (int i = 0; i < check_no.length; i++) {
				noticeService.noticeDelete(check_no[i]);
			}
			return "redirect:notice.do";
		}

}
