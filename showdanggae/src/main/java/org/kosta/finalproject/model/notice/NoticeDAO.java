package org.kosta.finalproject.model.notice;

import java.util.List;

public interface NoticeDAO {

	List<NoticeVO> noticeList(int pn);

	int getCount();

	void plusHit(int no);

	NoticeVO noticeContent(int no);

	void write(NoticeVO vo);

	void noticeUpdate(NoticeVO vo);

	void noticeDelete(String string);

}