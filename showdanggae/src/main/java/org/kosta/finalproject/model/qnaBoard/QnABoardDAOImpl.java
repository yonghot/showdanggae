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

	@Override
	public List<QnaVO> getBoardList(String pageNo) {

		return sqlSessionTemplate.selectList("qna.getBoardList", pageNo);
	}

	@Override
	public int totalContent() {
		return sqlSessionTemplate.selectOne("qna.totalContent");
	}

	@Override
	public QnaVO showContent(int no) {
		
		return sqlSessionTemplate.selectOne("qna.showContent", no);
	}

	@Override
	public void plushit(int no) {
		sqlSessionTemplate.update("qna.plushit", no);
	}

	@Override
	public void qnaWrite(QnaVO qvo) {
		sqlSessionTemplate.insert("qna.qnaWrite", qvo);
	}

	@Override
	public void qnaDelete(int num) {
		sqlSessionTemplate.delete("qna.qnaDelete",num);
	}

	@Override
	public QnaVO qnaUpdate(QnaVO qvo) {
		 sqlSessionTemplate.update("qna.qnaUpdate",qvo);
		return sqlSessionTemplate.selectOne("qna.showContent", qvo.getNo());
	}

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
