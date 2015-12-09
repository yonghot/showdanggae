package org.kosta.finalproject.model.qnaBoard;

public class ReplyVO {
	private int cno;
	private int no;
	private String member_name;
	private String member_id;
	private String replyComment;
	private String commentDate;

	
	
	public ReplyVO() {
		super();
		// TODO Auto-generated constructor stub
	}


	public ReplyVO(int cno, int no, String member_name, String member_id,
			String replyComment, String commentDate) {
		super();
		this.cno = cno;
		this.no = no;
		this.member_name = member_name;
		this.member_id = member_id;
		this.replyComment = replyComment;
		this.commentDate = commentDate;
	
	}


	public int getCno() {
		return cno;
	}


	public void setCno(int cno) {
		this.cno = cno;
	}


	public int getNo() {
		return no;
	}


	public void setNo(int no) {
		this.no = no;
	}


	public String getMember_name() {
		return member_name;
	}


	public void setMember_name(String member_name) {
		this.member_name = member_name;
	}


	public String getMember_id() {
		return member_id;
	}


	public void setMember_id(String member_id) {
		this.member_id = member_id;
	}


	public String getReplyComment() {
		return replyComment;
	}


	public void setReplyComment(String replyComment) {
		this.replyComment = replyComment;
	}


	public String getCommentDate() {
		return commentDate;
	}


	public void setCommentDate(String commentDate) {
		this.commentDate = commentDate;
	}




	@Override
	public String toString() {
		return "ReplyVO [cno=" + cno + ", no=" + no + ", member_name="
				+ member_name + ", member_id=" + member_id + ", replyComment="
				+ replyComment + ", commentDate=" + commentDate + "]";
	}


	
	
}