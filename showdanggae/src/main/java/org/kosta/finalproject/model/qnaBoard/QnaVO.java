package org.kosta.finalproject.model.qnaBoard;
/**
 * no : QnA 게시글 번호
 * title: QnA 게시글 제목
 * member_id:QnA 게시글 글쓴이의 아이디
 * writer:QnA 게시글 글쓴이의 이름
 * content:QnA 게시글의 내용
 * writeDate:QnA 게시글이 쓰여진 날짜
 * viewCount:QnA 게시글 의 조회수
 * ref:QnA 게시글 원 게시물 번호 , 글묶음 
 * restep: QnA 게시글ref 글묶음내의 글순서 
 * relevel:QnA 게시글 답변의 단계 
 * total: QnA 게시글의 총 게시물 수
 * @author 유서정
 *
 */
public class QnaVO {
	private int no;
	private String title;
	private String member_id;
	private String writer;
	private String content;
	private String writeDate;
	private int viewCount;
	private int ref;
	private int restep;
	private int relevel;
	private int total;

	public QnaVO() {
		super();
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
