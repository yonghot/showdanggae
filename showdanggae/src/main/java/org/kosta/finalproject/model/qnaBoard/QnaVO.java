package org.kosta.finalproject.model.qnaBoard;

public class QnaVO {
	private int no;
	private String title;
	private String member_id;
	private String writer;
	private String content;
	private String writeDate;
	private int viewCount;
	private int ref;// 원 게시물 번호 , 글묶음 
	private int restep;// ref 글묶음내의 글순서 
	private int relevel;// 답변의 단계 
	private int total;
	//no, title, member_id, writer, content, writeDate,viewCount,ref,restep,relevel
	public QnaVO() {
		super();
		// TODO Auto-generated constructor stub
	}
	public QnaVO(int no, String title, String member_id, String writer,
			String content, String writeDate, int viewCount, int ref,
			int restep, int relevel, int total) {
		super();
		this.no = no;
		this.title = title;
		this.member_id = member_id;
		this.writer = writer;
		this.content = content;
		this.writeDate = writeDate;
		this.viewCount = viewCount;
		this.ref = ref;
		this.restep = restep;
		this.relevel = relevel;
		this.total = total;
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
	public String getMember_id() {
		return member_id;
	}
	public void setMember_id(String member_id) {
		this.member_id = member_id;
	}
	public String getWriter() {
		return writer;
	}
	public void setWriter(String writer) {
		this.writer = writer;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public String getWriteDate() {
		return writeDate;
	}
	public void setWriteDate(String writeDate) {
		this.writeDate = writeDate;
	}
	public int getViewCount() {
		return viewCount;
	}
	public void setViewCount(int viewCount) {
		this.viewCount = viewCount;
	}
	public int getRef() {
		return ref;
	}
	public void setRef(int ref) {
		this.ref = ref;
	}
	public int getRestep() {
		return restep;
	}
	public void setRestep(int restep) {
		this.restep = restep;
	}
	public int getRelevel() {
		return relevel;
	}
	public void setRelevel(int relevel) {
		this.relevel = relevel;
	}
	public int getTotal() {
		return total;
	}
	public void setTotal(int total) {
		this.total = total;
	}
	@Override
	public String toString() {
		return "QnaVO [no=" + no + ", title=" + title + ", member_id="
				+ member_id + ", writer=" + writer + ", content=" + content
				+ ", writeDate=" + writeDate + ", viewCount=" + viewCount
				+ ", ref=" + ref + ", restep=" + restep + ", relevel="
				+ relevel + ", total=" + total + "]";
	}

	
	
	
	

}
