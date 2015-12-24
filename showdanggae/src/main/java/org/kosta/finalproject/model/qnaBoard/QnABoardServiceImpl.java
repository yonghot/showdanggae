package org.kosta.finalproject.model.qnaBoard;

import java.util.List;
import javax.annotation.Resource;
import org.kosta.finalproject.model.member.MemberVO;
import org.springframework.stereotype.Service;

@Service
public class QnABoardServiceImpl implements QnABoardService {

	@Resource
	private QnABoardDAO qnaBoardDAO;

	/**
	 * 
	 * @Method Name  : getBoardList
	 * @작성일   : 2015. 12. 23. 
	 * @작성자   : 유서정
	 * @변경이력  :
	 * @Method 설명 :회원이 쓴 질문과 관리자가 답변을 달아준 정보들을 list로
	 * 							질문과 답변 양이 한 페이지에 보여줄 수 있는 양보다 많을 수 있으므로
	 * 							pageNo를 받아 그 페이지에 해당하는 질문과 답변 리스트를 보여준다
	 * @param pageNo
	 * @return
	 */
	@Override
	public QnaListVO getBoardList(String pageNo) {
		if (pageNo == null || pageNo == "") {
			pageNo = "1";
		}
		List<QnaVO> list = qnaBoardDAO.getBoardList(pageNo);
		int total = qnaBoardDAO.totalContent();
		QPagingBean qpagingBean = new QPagingBean(total,
				Integer.parseInt(pageNo));
		QnaListVO qvo = new QnaListVO(list, qpagingBean);
		return qvo;
	}

	/**
	 * 
	 * @Method Name  : plushit
	 * @작성일   : 2015. 12. 23. 
	 * @작성자   : 유서정
	 * @변경이력  :
	 * @Method 설명 : 해당 QnA 게시글을 선택하면 조회수를 증가시켜준다
	 * @param no
	 */
	@Override
	public void plushit(int no) {
		qnaBoardDAO.plushit(no);
	}

	/**
	 * 
	 * @Method Name  : showContent
	 * @작성일   : 2015. 12. 23. 
	 * @작성자   : 유서정
	 * @변경이력  :
	 * @Method 설명 : 해당 QnA 게시글을 선택하면 선택한 내용을 보여준다
	 * @param no
	 * @return
	 */
	@Override
	public QnaVO showContent(int no) {
		return qnaBoardDAO.showContent(no);
	}

	/**
	 * 
	 * @Method Name  : qnaWrite
	 * @작성일   : 2015. 12. 23. 
	 * @작성자   : 유서정
	 * @변경이력  :
	 * @Method 설명 : 새롭게 작성한 QnA 글을 저장해준다
	 * @param qvo
	 */
	@Override
	public void qnaWrite(QnaVO qvo) {
		qnaBoardDAO.qnaWrite(qvo);
	}

	/**
	 * 
	 * @Method Name  : qnaDelete
	 * @작성일   : 2015. 12. 23. 
	 * @작성자   : 유서정
	 * @변경이력  :
	 * @Method 설명 : 선택한 QnA 번호를 받아와 해당하는 정보를 저장한다
	 * @param no
	 */
	@Override
	public void qnaDelete(String no) {
		int num = Integer.parseInt(no);
		qnaBoardDAO.qnaDelete(num);
	}

	/**
	 * 
	 * @Method Name  : qnaUpdate
	 * @작성일   : 2015. 12. 23. 
	 * @작성자   : 유서정
	 * @변경이력  :
	 * @Method 설명 : QnA 게시판에 저장된 게시글정보를 수정한다
	 * @param qvo
	 * @return
	 */
	@Override
	public QnaVO qnaUpdate(QnaVO qvo) {
		return qnaBoardDAO.qnaUpdate(qvo);
	}

	/**
	 * 
	 * @Method Name  : reply
	 * @작성일   : 2015. 12. 23. 
	 * @작성자   : 유서정
	 * @변경이력  :
	 * @Method 설명 : 관리자가 회원들이 질문한 QnA에 답글을 달아준다
	 * @param qvo
	 */
	@Override
	public void reply(QnaVO qvo) {
		int ref = qvo.getRef();
		int restep = qvo.getRestep();
		int relevel = qvo.getRelevel();

		qnaBoardDAO.updateRestep(ref, restep);
		// 먼저 update 해서 ref와 restep 을 1씩 증가시켜줌
		qvo.setRestep(restep + 1);
		qvo.setRelevel(relevel + 1);
		
		qnaBoardDAO.insertRefContent(qvo);
	}

	/**
	 * 
	 * @Method Name  : commentInsert
	 * @작성일   : 2015. 12. 23. 
	 * @작성자   : 유서정
	 * @변경이력  :
	 * @Method 설명 : QnA게시글에 댓글을 등록
	 * @param no
	 * @param replyComment
	 * @param mvo
	 */
	@Override
	public void commentInsert(int no, String replyComment, MemberVO mvo) {
		ReplyVO rvo = new ReplyVO();
		// 댓글 정보들을 저장하는 객체
		rvo.setNo(no);
		rvo.setReplyComment(replyComment);
		rvo.setMember_name(mvo.getMember_name());
		rvo.setMember_id(mvo.getMember_id());

		qnaBoardDAO.commentInsert(rvo);
		qnaBoardDAO.commentTotal(no);

	}

	/**
	 * 
	 * @Method Name  : showReplyComment
	 * @작성일   : 2015. 12. 23. 
	 * @작성자   : 유서정
	 * @변경이력  :
	 * @Method 설명 :QnA의 게시물에 달린 댓글리스트를 보여준다
	 * @param num
	 * @return
	 */
	@Override
	public List<ReplyVO> showReplyComment(int num) {
		return qnaBoardDAO.showReplyComment(num);
	}

	/**
	 * 
	 * @Method Name  : deleteComment
	 * @작성일   : 2015. 12. 23. 
	 * @작성자   : 유서정
	 * @변경이력  :
	 * @Method 설명 :QnA의 게시물에 달린 댓글을 삭제한다
	 * @param cno
	 * @param no
	 */
	@Override
	public void deleteComment(int cno, int no) {
		qnaBoardDAO.deleteComment(cno);
		qnaBoardDAO.commentTotalminus(no);
	}

}
