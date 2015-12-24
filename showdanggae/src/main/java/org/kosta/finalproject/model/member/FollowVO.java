package org.kosta.finalproject.model.member;
/**
 * 
 * @Method 설명 : FollowVO
 * following 날짜, following , follower
 * @작성일 : 2015. 12. 22.
 * @작성자 : KOSTA
 */
public class FollowVO {
	private String following_date;
	private String following;
	private String follower;
	public FollowVO(String following_date, String following, String follower) {
		super();
		this.following_date = following_date;
		this.following = following;
		this.follower = follower;
	}
	public FollowVO() {
		super();
		// TODO Auto-generated constructor stub
	}
	public String getFollowing_date() {
		return following_date;
	}
	public void setFollowing_date(String following_date) {
		this.following_date = following_date;
	}
	public String getFollowing() {
		return following;
	}
	public void setFollowing(String following) {
		this.following = following;
	}
	public String getFollower() {
		return follower;
	}
	public void setFollower(String follower) {
		this.follower = follower;
	}
	@Override
	public String toString() {
		return "FollowVO [following_date=" + following_date + ", following="
				+ following + ", follower=" + follower + "]";
	}
}
