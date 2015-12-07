package org.kosta.finalproject.model.qnaBoard;

import org.kosta.finalproject.model.member.MemberVO;

public interface QnABoardService {

	QnaListVO getBoardList(String pageNo);

	QnaVO showContent(int no);

	void plushit(int no);

	void qnaWrite(QnaVO qvo);

	void qnaDelete(String no);

	QnaVO qnaUpdate(QnaVO qvo);

	void reply(QnaVO qvo);

	void commentInsert(int no, String replyComment, MemberVO mvo);

}
