package org.kosta.finalproject.model.qnaBoard;

import java.util.HashMap;
import java.util.List;

import javax.annotation.Resource;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class QnABoardDAOImpl implements QnABoardDAO{
	@Resource
	private SqlSessionTemplate sqlSessionTemplate;

	/**
	 * 
	 * @Method Name  : getBoardList
	 * @작성일   : 2015. 12. 24. 
	 * @작성자   : 유서정
	 * @변경이력  :
	 * @Method 설명 : 전달받은 파라미터 Page번호에 해당하는 QnA 게시물 리스트를 검색
	 * @param pageNo
	 * @return
	 */
	@Override
	public List<QnaVO> getBoardList(String pageNo) {
		return sqlSessionTemplate.selectList("qna.getBoardList", pageNo);
	}

	/**
	 * 
	 * @Method Name  : totalContent
	 * @작성일   : 2015. 12. 24. 
	 * @작성자   : 유서정
	 * @변경이력  :
	 * @Method 설명 :QnA 게시글의 총 개수 검색
	 * @return
	 */
	@Override
	public int totalContent() {
		return sqlSessionTemplate.selectOne("qna.totalContent");
	}

	/**
	 * 
	 * @Method Name  : showContent
	 * @작성일   : 2015. 12. 24. 
	 * @작성자   : 유서정
	 * @변경이력  :
	 * @Method 설명 : QnA 게시글 중 선택한 게시글의 번호를 받아와 그에 해당하는 내용 검색
	 * @param no
	 * @return
	 */
	@Override
	public QnaVO showContent(int no) {
		return sqlSessionTemplate.selectOne("qna.showContent", no);
	}

	/**
	 * 
	 * @Method Name  : plushit
	 * @작성일   : 2015. 12. 24. 
	 * @작성자   : 유서정
	 * @변경이력  :
	 * @Method 설명 : 회원과 관리자가 qna 게시글을 조회하려고클릭했을시에 조회수를 1씩 추가해 수정
	 * @param no
	 */
	@Override
	public void plushit(int no) {
		sqlSessionTemplate.update("qna.plushit", no);
	}

	/**
	 * 
	 * @Method Name  : qnaWrite
	 * @작성일   : 2015. 12. 24. 
	 * @작성자   : 유서정
	 * @변경이력  :
	 * @Method 설명 :QnA 게시판에 게시한 글을 저장
	 * @param qvo
	 */
	@Override
	public void qnaWrite(QnaVO qvo) {
		sqlSessionTemplate.insert("qna.qnaWrite", qvo);
	}

	/**
	 * 
	 * @Method Name  : qnaDelete
	 * @작성일   : 2015. 12. 24. 
	 * @작성자   : 유서정
	 * @변경이력  :
	 * @Method 설명 :QnA 게시판에서 입력받은 글번호와 일치하는 게시글을 삭제
	 * @param num
	 */
	@Override
	public void qnaDelete(int num) {
		sqlSessionTemplate.delete("qna.qnaDelete",num);
	}

	/**
	 * 
	 * @Method Name  : qnaUpdate
	 * @작성일   : 2015. 12. 24. 
	 * @작성자   : 유서정
	 * @변경이력  :
	 * @Method 설명 :QnA 게시판에서 게시한 게시글 새로운을 수정
	 * @param qvo
	 * @return
	 */
	@Override
	public QnaVO qnaUpdate(QnaVO qvo) {
		 sqlSessionTemplate.update("qna.qnaUpdate",qvo);
		return sqlSessionTemplate.selectOne("qna.showContent", qvo.getNo());
	}

	/**
	 * 
	 * @Method Name  : updateRestep
	 * @작성일   : 2015. 12. 24. 
	 * @작성자   : 유서정
	 * @변경이력  :
	 * @Method 설명 :
	 * @param ref
	 * @param restep
	 */
	@Override
	public void updateRestep(int ref, int restep) {
		HashMap<String, Integer> map = new HashMap<String, Integer>();
		map.put("ref", ref);
		map.put("restep", restep);
		sqlSessionTemplate.update("qna.updateRestep", map);			
	}

	@Override
	public void insertRefContent(QnaVO qvo) {
		//sqlSessionTemplate.insert("board.writeReply", bvo);		
		sqlSessionTemplate.insert("qna.writeReply", qvo);
	}

	@Override
	public void commentInsert(ReplyVO rvo) {
		sqlSessionTemplate.insert("qna.commentInsert",rvo);
	}

	@Override
	public List<ReplyVO> showReplyComment(int num) {
		return sqlSessionTemplate.selectList("qna.showReplyComment", num);
	}

	@Override
	public void commentTotal(int no) {
		sqlSessionTemplate.update("qna.commentTotal", no);
		
	}

	@Override
	public void deleteComment(int cno) {
		// 댓글지우기
		sqlSessionTemplate.delete("qna.deleteComment", cno);
		
	}

	@Override
	public void commentTotalminus(int no) {
		// 댓글 마이너스
		sqlSessionTemplate.update("qna.commentTotalminus", no);
	}

}
