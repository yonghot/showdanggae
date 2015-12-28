package org.kosta.finalproject.controller;

import java.util.List;

import javax.annotation.Resource;
import javax.servlet.ServletRequest;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.kosta.finalproject.model.member.MemberVO;
import org.kosta.finalproject.model.qnaBoard.QnABoardService;
import org.kosta.finalproject.model.qnaBoard.QnaListVO;
import org.kosta.finalproject.model.qnaBoard.QnaVO;
import org.kosta.finalproject.model.qnaBoard.ReplyVO;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class QnABoardController {
	
	@Resource
	private QnABoardService qnaBoardService;
	
	/**
	 * 
	 * @Method Name  : qnaboard
	 * @작성일   : 2015. 12. 22. 
	 * @작성자   : 유서정
	 * @변경이력  :
	 * @Method 설명 :회원들이 쓴 질문들을  list로 보여준다
	 * 							한 페이지당 보여지는 리스트 갯수를 초과할 수 있으므로
	 * 							page를 변수로 받아 그에 해당하는 list 결과를 보여준다
	 * @param pageNo
	 * @return
	 */
	@RequestMapping("qnaboard.do")
	public ModelAndView qnaboard(String pageNo){
		System.out.println(qnaBoardService.getBoardList(pageNo));
		return new ModelAndView("qna_qnaList","qvo",qnaBoardService.getBoardList(pageNo));
	}
	
	/**
	 * 
	 * @Method Name  : showContent
	 * @작성일   : 2015. 12. 23. 
	 * @작성자   : 유서정
	 * @변경이력  :
	 * @Method 설명 : QnA list중 하나의 QnA를 선택하면 선택된 QnA의 내용을 보여준다
	 * @param no
	 * @return
	 */
	@RequestMapping("showContent.do")
	public ModelAndView showContent(int no){
		qnaBoardService.plushit(no);
		return new ModelAndView("redirect:nohitshowContent.do?no=" + no);
	}
	
	/**
	 * 
	 * @Method Name  : nohitshowContent
	 * @작성일   : 2015. 12. 23. 
	 * @작성자   : 유서정
	 * @변경이력  :
	 * @Method 설명 : 새로고침시 조회수 증가를 막아준다
	 * @param no
	 * @return
	 */
	@RequestMapping("nohitshowContent.do")
	public ModelAndView nohitshowContent(String no){
		int num = Integer.parseInt(no);
		QnaVO qvo=qnaBoardService.showContent(num);
		 List<ReplyVO> rvo=qnaBoardService.showReplyComment(num);
		 ModelAndView mav= new ModelAndView();
		 mav.setViewName("qna_qnashowview");
		 mav.addObject("replycomment", rvo);
		 mav.addObject("content", qvo);
		 
		return mav;
	}
	
	/**
	 * 
	 * @Method Name  : qnaWriteForm
	 * @작성일   : 2015. 12. 23. 
	 * @작성자   : 유서정
	 * @변경이력  :
	 * @Method 설명 : 회원들이 QnA 게시판에 글을 쓸 수 있는 FORM을 제공해주는 페이지로 이동
	 * @return
	 */
	@RequestMapping("qnaWriteForm.do")
	public String qnaWriteForm(){
		return "qna_qnaWriteForm";
	}
	
	/**
	 * 
	 * @Method Name  : qnaWritecancel
	 * @작성일   : 2015. 12. 23. 
	 * @작성자   : 유서정
	 * @변경이력  :
	 * @Method 설명 :QnA게시판 글작성 도중에 취소버튼을 누르면 홈페이지로 이동시킨다
	 * @return
	 */
	@RequestMapping("qnaWritecancel.do")
	public String qnaWritecancel(){
		//글쓰기 도중 취소 눌렀을때
		return "qna_qnaList";
	}
	
	/**
	 * 
	 * @Method Name  : qnaWrite
	 * @작성일   : 2015. 12. 23. 
	 * @작성자   : 유서정
	 * @변경이력  :
	 * @Method 설명 : 회원이 QnA에 게시글을 등록하면 해당 게시글을 보여준다
	 * @param qvo
	 * @return
	 */
	@RequestMapping("qnawrite.do")
	public ModelAndView qnaWrite(QnaVO qvo){
		qnaBoardService.qnaWrite(qvo);
		return new ModelAndView("redirect:nohitshowContent.do?no=" + qvo.getNo());
	}
	
	/**
	 * 
	 * @Method Name  : qnaDelete
	 * @작성일   : 2015. 12. 23. 
	 * @작성자   : 유서정
	 * @변경이력  :
	 * @Method 설명 :QnA게시판 중 선택된 게시글을 삭제한다
	 * @param no
	 * @return
	 */
	@RequestMapping("qnaDelete.do")
	public String qnaDelete(String no){
		qnaBoardService.qnaDelete(no);
		return "redirect:qnaboard.do";
	}
	
	/**
	 * 
	 * @Method Name  : qnaUpdateForm
	 * @작성일   : 2015. 12. 23. 
	 * @작성자   : 유서정
	 * @변경이력  :
	 * @Method 설명 :QnA게시판 중 선택한 게시글을 수정할 수 있도록 수정 FORM으로 이동시킨다
	 * @param no
	 * @return
	 */
	@RequestMapping("qnaUpdateForm.do")
	public ModelAndView qnaUpdateForm(String no){
		int num = Integer.parseInt(no);
		QnaVO qvo=qnaBoardService.showContent(num);
		return new ModelAndView("qna_qnaUpdateForm","qvo",qvo);
	}
	
	/**
	 * 
	 * @Method Name  : qnaUpdate
	 * @작성일   : 2015. 12. 23. 
	 * @작성자   : 유서정
	 * @변경이력  :
	 * @Method 설명 :QnA에 등록한 게시글을 수정한 내용을 저장한다
	 * @param qvo
	 * @return
	 */
	@RequestMapping("qnaUpdate.do")
	public ModelAndView qnaUpdate(QnaVO qvo){
		QnaVO afterQvo=qnaBoardService.qnaUpdate(qvo);
		//nohitshowContent
		return new ModelAndView("qna_qnashowview","content",afterQvo);
	}
	
	/**
	 * 
	 * @Method Name  : replyView
	 * @작성일   : 2015. 12. 23. 
	 * @작성자   : 유서정
	 * @변경이력  :
	 * @Method 설명 :회원들이 남김QnA에 관리자가 답변을 달 수 있도록 답변을 쓰는 FORM으로 이동한다
	 * @param no
	 * @return
	 */
	@RequestMapping("replyView.do")
	public ModelAndView replyView(String no){
		int num=Integer.parseInt(no);
		return new ModelAndView("qna_reply_from","qvo",qnaBoardService.showContent(num));
	}
	
	/**
	 * 
	 * @Method Name  : reply
	 * @작성일   : 2015. 12. 23. 
	 * @작성자   : 유서정
	 * @변경이력  :
	 * @Method 설명 :관리자의 답글을 등록한 후 게시된 글을 보여준다
	 * @param qvo
	 * @return
	 */
	@RequestMapping("reply.do")
	public ModelAndView reply(QnaVO qvo){
		qnaBoardService.reply(qvo);	
		return new ModelAndView("redirect:nohitshowContent.do?no="+qvo.getNo());
	}
	
	/**
	 * 
	 * @Method Name  : comment
	 * @작성일   : 2015. 12. 23. 
	 * @작성자   : 유서정
	 * @변경이력  :
	 * @Method 설명 :회원들이 QnA게시판에 등록된 게시글에 댓글을 달 수 있게해준다
	 * @param no
	 * @param request
	 * @param replyComment
	 * @return
	 */
	@RequestMapping("comment.do")
	public ModelAndView comment(int no,HttpServletRequest request,String replyComment){
		HttpSession session=request.getSession(false);
		MemberVO mvo=(MemberVO) session.getAttribute("mvo"); //지금 로그인 한 사람
		qnaBoardService.commentInsert(no,replyComment,mvo);
		return new ModelAndView("redirect:recomment.do?no="+ no);
	}
	
	/**
	 * 
	 * @Method Name  : recomment
	 * @작성일   : 2015. 12. 23. 
	 * @작성자   : 유서정
	 * @변경이력  :
	 * @Method 설명 :회원들이 댓글을 달고나서 F5(새로고침)을 눌렀을때 중복 수행을 피한다
	 * @param no
	 * @return
	 */
	@RequestMapping("recomment.do")
	public String recomment(int no){
		return "redirect:nohitshowContent.do?no="+no;
	}
	
	/**
	 * 
	 * @Method Name  : deleteComment
	 * @작성일   : 2015. 12. 23. 
	 * @작성자   : 유서정
	 * @변경이력  :
	 * @Method 설명 :QnA에 달은 댓글을 삭제한다
	 * @param cno
	 * @param no
	 * @return
	 */
	@RequestMapping("deleteComment.do")
	public String deleteComment(int cno,int no){
		qnaBoardService.deleteComment(cno,no);
		return  "redirect:nohitshowContent.do?no="+no;
	}
	

}
