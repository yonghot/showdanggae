package org.kosta.finalproject.model.member;

/**
 * member_id : 회원 id
 * password : 회원 비밀번호
 * member_name: 회원 이름
 * member_info: 회원 소개글
 * email: 회원의 email id와 domain 통합한 주소
 * email_id: 회원의 email id
 * email_domain:회원의 email domain
 * birthday:회원의 생년월일 여섯자리
 * report: 회원이 신고받은 횟수
 * isFollow: 다른 회원이 나를 팔로우한 
 * @author 유서정
 *
 */
public class MemberVO {
	private String member_id;
	private String password;
	private String member_name;
	private String member_info;
	private String email;
	private String email_id;
	private String email_domain;
	private String birthday;
	private int report;
	private Boolean isFollow;
	
	
	public MemberVO() {
		super();
	}


	public MemberVO(String member_id, String password, String member_name,
			String member_info, String email, String email_id,
			String email_domain, String birthday, int report, Boolean isFollow) {
		super();
		this.member_id = member_id;
		this.password = password;
		this.member_name = member_name;
		this.member_info = member_info;
		this.email = email;
		this.email_id = email_id;
		this.email_domain = email_domain;
		this.birthday = birthday;
		this.report = report;
		this.isFollow = isFollow;
	}


	public String getMember_id() {
		return member_id;
	}


	public void setMember_id(String member_id) {
		this.member_id = member_id;
	}


	public String getPassword() {
		return password;
	}


	public void setPassword(String password) {
		this.password = password;
	}


	public String getMember_name() {
		return member_name;
	}


	public void setMember_name(String member_name) {
		this.member_name = member_name;
	}


	public String getMember_info() {
		return member_info;
	}


	public void setMember_info(String member_info) {
		this.member_info = member_info;
	}


	public String getEmail() {
		return email;
	}


	public void setEmail(String email) {
		this.email = email;
	}


	public String getEmail_id() {
		return email_id;
	}


	public void setEmail_id(String email_id) {
		this.email_id = email_id;
	}


	public String getEmail_domain() {
		return email_domain;
	}


	public void setEmail_domain(String email_domain) {
		this.email_domain = email_domain;
	}


	public String getBirthday() {
		return birthday;
	}


	public void setBirthday(String birthday) {
		this.birthday = birthday;
	}


	public int getReport() {
		return report;
	}


	public void setReport(int report) {
		this.report = report;
	}


	public Boolean getIsFollow() {
		return isFollow;
	}


	public void setIsFollow(Boolean isFollow) {
		this.isFollow = isFollow;
	}


	@Override
	public String toString() {
		return "MemberVO [member_id=" + member_id + ", password=" + password
				+ ", member_name=" + member_name + ", member_info="
				+ member_info + ", email=" + email + ", email_id=" + email_id
				+ ", email_domain=" + email_domain + ", birthday=" + birthday
				+ ", report=" + report + ", isFollow=" + isFollow + "]";
	}

	
	
	
}

