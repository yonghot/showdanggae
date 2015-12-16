package org.kosta.finalproject.model.notice;


public interface NoticeService {

	ListVO noticeList(String pageNo);

	NoticeVO noticeContent(int no);

	NoticeVO noHitnoticeContent(int no);

	void write(NoticeVO vo);

	NoticeVO noticeUpdate(NoticeVO vo);

	void noticeDelete(String string);

	void deleteContent(int no); 

}
