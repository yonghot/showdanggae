package org.kosta.finalproject.model.message;

public class MessageVO {
	private String member_id;
	private String message;
	private String spand_name;
	private String title;
	private String spand_date;
	private int mno;
	private int read;
	
	

	
	public MessageVO() {
		super();
		// TODO Auto-generated constructor stub
	}







	public MessageVO(String member_id, String message, String spand_name,
			String title, String spand_date, int mno, int read) {
		super();
		this.member_id = member_id;
		this.message = message;
		this.spand_name = spand_name;
		this.title = title;
		this.spand_date = spand_date;
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




	public String getSpand_name() {
		return spand_name;
	}




	public void setSpand_name(String spand_name) {
		this.spand_name = spand_name;
	}




	public String getTitle() {
		return title;
	}




	public void setTitle(String title) {
		this.title = title;
	}




	public String getSpand_date() {
		return spand_date;
	}




	public void setSpand_date(String spand_date) {
		this.spand_date = spand_date;
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
				+ ", spand_name=" + spand_name + ", title=" + title
				+ ", spand_date=" + spand_date + ", mno=" + mno + ", read="
				+ read + "]";
	}


}
