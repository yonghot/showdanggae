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
	
	@RequestMapping("qnaboard.do")
	public ModelAndView qnaboard(String pageNo){
		return new ModelAndView("qna_qnaList","qvo",qnaBoardService.getBoardList(pageNo));
	}
	
	@RequestMapping("showContent.do")
	public ModelAndView showContent(int no){
		//조회수 증가
		qnaBoardService.plushit(no);
		return new ModelAndView("redirect:nohitshowContent.do?no=" + no);
	}
	
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
	
	@RequestMapping("qnaWriteForm.do")
	public String qnaWriteForm(){
		//글쓰기
		return "qna_qnaWriteForm";
	}
	
	@RequestMapping("qnaWritecancel.do")
	public String qnaWritecancel(){
		//글쓰기 도중 취소 눌렀을때
		return "qna_qnaList";
	}
	
	@RequestMapping("qnawrite.do")
	public ModelAndView qnaWrite(QnaVO qvo){
		//쓰고 난 후 쓴거 보여주기 redirect 해줘야함 no로
		//제목 이름 content
		qnaBoardService.qnaWrite(qvo);
		return new ModelAndView("redirect:nohitshowContent.do?no=" + qvo.getNo());
	}
	
	@RequestMapping("qnaDelete.do")
	public String qnaDelete(String no){
		System.out.println("삭제"  + no);
		qnaBoardService.qnaDelete(no);
		return "redirect:qnaboard.do";
	}
	
	@RequestMapping("qnaUpdateForm.do")
	public ModelAndView qnaUpdateForm(String no){
		int num = Integer.parseInt(no);
		QnaVO qvo=qnaBoardService.showContent(num);
		return new ModelAndView("qna_qnaUpdateForm","qvo",qvo);
	}
	
	@RequestMapping("qnaUpdate.do")
	public ModelAndView qnaUpdate(QnaVO qvo){
		QnaVO afterQvo=qnaBoardService.qnaUpdate(qvo);
		//nohitshowContent
		return new ModelAndView("qna_qnashowview","content",afterQvo);
	}
	
	@RequestMapping("replyView.do")
	public ModelAndView replyView(String no){
		int num=Integer.parseInt(no);
		//QnaVO qvo=qnaBoardService.showContent(num); 이거 no hit임
		return new ModelAndView("qna_reply_from","qvo",qnaBoardService.showContent(num));
	}
	
	@RequestMapping("reply.do")
	public ModelAndView reply(QnaVO qvo){
		
		qnaBoardService.reply(qvo);
		
		return new ModelAndView("redirect:nohitshowContent.do?no="+qvo.getNo());
	}
	
	@RequestMapping("comment.do")
	public ModelAndView comment(int no,HttpServletRequest request,String replyComment){
		
		HttpSession session=request.getSession(false);
		MemberVO mvo=(MemberVO) session.getAttribute("mvo"); //지금 로그인 한 사람
		qnaBoardService.commentInsert(no,replyComment,mvo);
		
		return new ModelAndView("redirect:recomment.do?no="+ no);
	}
	
	@RequestMapping("recomment.do")
	public String recomment(int no){
		return "redirect:nohitshowContent.do?no="+no;
	}
	
	@RequestMapping("deleteComment.do")
	public String deleteComment(int cno,int no){
		qnaBoardService.deleteComment(cno,no);
		return  "redirect:nohitshowContent.do?no="+no;
	}
	

}
