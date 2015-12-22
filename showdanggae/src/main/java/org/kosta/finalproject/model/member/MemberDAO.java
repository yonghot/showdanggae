package org.kosta.finalproject.model.member;

import java.util.HashMap;
import java.util.List;



public interface MemberDAO {
	
	
	//BBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBB

	MemberVO idCheck(String beforeId);
	MemberVO register(MemberVO vo);
	MemberVO login(MemberVO vo);
	MemberVO findIdByEmail(MemberVO vo);
	MemberVO findPassById(MemberVO vo);
	void updateMember(MemberVO vo);
	MemberVO updateOk(String member_id);
	void withdraw(String member_id);
	MemberVO adminlogin(MemberVO vo);
	List<MemberVO> memberManagerList(int pn);
	void memberDelete(String member_id);
	int getCount();

	
	//CCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCC
	
	List<MemberVO> findMemberById(String id);
	public void addFollow(FollowVO fvo);
	public void deleteFollow(FollowVO vo);
	List<FollowVO> findFollowingId(String member_id);
	List<FollowVO> findFollowerId(String member_id);
	String findIsFollowBySessionId(HashMap<String, String> map);
	List<MemberVO> onkeyupId(String searchId);
	List<FollowVO> fAlarm(String following);
	int followerCount(String member_id);
	int followingCount(String member_id);
	List<String> profileInterest(String member_id);
	List<String> profileInterestList(String member_id);
	List<String> myinterestList(String member_id);
	MemberVO infoUpdate(MemberVO vo);
	void interestAdd(HashMap<String, String> add);
	void interestDel(HashMap<String, String> del);
	int productCount(String member_id);


}