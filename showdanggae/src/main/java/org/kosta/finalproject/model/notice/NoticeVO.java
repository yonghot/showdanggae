package org.kosta.finalproject.model.notice;
/**
 * no:  공지사항 게시글의 번호
 * title: 공지사항 게시글의 제목
 * content:공지사항 게시글의 내용
 * hit:공지사항 게시글의 조회수
 * writer:공지사항 게시글의 글쓴이
 * writeDate:공지사항 게시글이 쓰여진 날짜
 * 
 * @author 유서정
 *
 */
public class NoticeVO {
	private int no;
	private String title; 
	private String content;
	private int hit;
	private String writer;
	private String writeDate;
	
	public NoticeVO() {
		super();
		// TODO Auto-generated constructor stub
	}

	public NoticeVO(int no, String title, String content, int hit,
			String writer, String writeDate) {
		super();
		this.no = no;
		this.title = title;
		this.content = content;
		this.hit = hit;
		this.writer = writer;
		this.writeDate = writeDate;
	}

	public int getNo() {
		return no;
	}

	public void setNo(int no) {
		this.no = no;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public int getHit() {
		return hit;
	}

	public void setHit(int hit) {
		this.hit = hit;
	}

	public String getWriter() {
		return writer;
	}

	public void setWriter(String writer) {
		this.writer = writer;
	}

	public String getWriteDate() {
		return writeDate;
	}

	public void setWriteDate(String writeDate) {
		this.writeDate = writeDate;
	}

	@Override
	public String toString() {
		return "NoticeVO [no=" + no + ", title=" + title + ", content="
				+ content + ", hit=" + hit + ", writer=" + writer
				+ ", writeDate=" + writeDate + "]";
	}

	
}
