package org.kosta.finalproject.model.qnaBoard;

import java.util.List;

import javax.annotation.Resource;

import org.kosta.finalproject.model.member.MemberVO;
import org.springframework.stereotype.Service;


@Service
public class QnABoardServiceImpl implements QnABoardService{
	
	@Resource
	private QnABoardDAO qnaBoardDAO;

	@Override
	public QnaListVO getBoardList(String pageNo) {
		if(pageNo==null||pageNo=="") 
			pageNo="1";
		List<QnaVO> list=qnaBoardDAO.getBoardList(pageNo);
		int total=qnaBoardDAO.totalContent();
		QPagingBean qpagingBean=new QPagingBean(total,Integer.parseInt(pageNo));
		QnaListVO qvo=new QnaListVO(list,qpagingBean);
		return qvo;
	}
	@Override
	public void plushit(int no) {
		qnaBoardDAO.plushit(no);
	}
	
	@Override
	public QnaVO showContent(int no) {
		
		return qnaBoardDAO.showContent(no);
	}
	@Override
	public void qnaWrite(QnaVO qvo) {
		qnaBoardDAO.qnaWrite(qvo);
		
	}
	@Override
	public void qnaDelete(String no) {
		int num=Integer.parseInt(no);
		qnaBoardDAO.qnaDelete(num);
	}
	@Override
	public QnaVO qnaUpdate(QnaVO qvo) {
	
		return qnaBoardDAO.qnaUpdate(qvo);
	}
	@Override
	public void reply(QnaVO qvo) {
		// TODO Auto-generated method stub
		int ref = qvo.getRef();
		int restep = qvo.getRestep();
		int relevel = qvo.getRelevel();
		
		qnaBoardDAO.updateRestep(ref, restep);
		//먼저 update 해서 ref와 restep 을 1씩 증가시켜줌
		qvo.setRestep(restep+1);
		qvo.setRelevel(relevel+1);
		System.out.println("service 부분 : "  + qvo);
		qnaBoardDAO.insertRefContent(qvo);//답변 글 입력		
	}
	@Override
	public void commentInsert(int no, String replyComment, MemberVO mvo) {
		//reply에 합쳐야햄
		ReplyVO rvo=new ReplyVO();
		rvo.setNo(no);
		rvo.setReplyComment(replyComment);
		rvo.setMember_name(mvo.getMember_name());
		System.out.println("확인" + mvo);
		rvo.setMember_id(mvo.getMember_id());
		
		qnaBoardDAO.commentInsert(rvo);
		qnaBoardDAO.commentTotal(no);
	
	}
	@Override
	public  List<ReplyVO> showReplyComment(int num) {
		
		return qnaBoardDAO.showReplyComment(num);
	}
	@Override
	public void deleteComment(int cno,int no) {
		qnaBoardDAO.deleteComment(cno);
		qnaBoardDAO.commentTotalminus(no);
		
	}
	
	

}
