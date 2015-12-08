package org.kosta.finalproject.model.qnaBoard;

import java.util.List;

public interface QnABoardDAO {

	List<QnaVO> getBoardList(String pageNo);

	int totalContent();

	QnaVO showContent(int no);

	void plushit(int no);

	void qnaWrite(QnaVO qvo);

	void qnaDelete(int num);

	QnaVO qnaUpdate(QnaVO qvo);

	void updateRestep(int ref, int restep);

	void insertRefContent(QnaVO qvo);

	void commentInsert(ReplyVO rvo);

}
