package org.kosta.finalproject.model.notice;

public class NoticeVO {
	private int no;
	private String title; 
	private String writer;
	private String password;
	private String content;
	private int hit;
	private String time_post;
	
	public NoticeVO() {
		super();
		// TODO Auto-generated constructor stub
	}

	public NoticeVO(int no, String title, String writer, String password,
			String content, int hit, String time_post) {
		super();
		this.no = no;
		this.title = title;
		this.writer = writer;
		this.password = password;
		this.content = content;
		this.hit = hit;
		this.time_post = time_post;
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

	public String getWriter() {
		return writer;
	}

	public void setWriter(String writer) {
		this.writer = writer;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
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

	public String getTime_post() {
		return time_post;
	}

	public void setTime_post(String time_post) {
		this.time_post = time_post;
	}

	@Override
	public String toString() {
		return "NoticeVO [no=" + no + ", title=" + title + ", writer=" + writer
				+ ", password=" + password + ", content=" + content + ", hit="
				+ hit + ", time_post=" + time_post + "]";
	}
	
	

	
	
	
}
