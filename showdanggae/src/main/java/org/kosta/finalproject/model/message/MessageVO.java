package org.kosta.finalproject.model.message;

public class MessageVO {
	private String member_id;
	private String message;
	private String sender;
	private String title;
	private String send_date;
	private int mno;
	private int read;

	public MessageVO() {
		super();
		// TODO Auto-generated constructor stub
	}

	public MessageVO(String member_id, String message, String sender,
			String title, String send_date, int mno, int read) {
		super();
		this.member_id = member_id;
		this.message = message;
		this.sender = sender;
		this.title = title;
		this.send_date = send_date;
		this.mno = mno;
		this.read = read;
	}

	public String getMember_id() {
		return member_id;
	}

	public void setMember_id(String member_id) {
		this.member_id = member_id;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public String getSender() {
		return sender;
	}

	public void setSender(String sender) {
		this.sender = sender;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getSend_date() {
		return send_date;
	}

	public void setSend_date(String send_date) {
		this.send_date = send_date;
	}

	public int getMno() {
		return mno;
	}

	public void setMno(int mno) {
		this.mno = mno;
	}

	public int getRead() {
		return read;
	}

	public void setRead(int read) {
		this.read = read;
	}

	@Override
	public String toString() {
		return "MessageVO [member_id=" + member_id + ", message=" + message
				+ ", sender=" + sender + ", title=" + title + ", send_date="
				+ send_date + ", mno=" + mno + ", read=" + read + "]";
	}

	

}
