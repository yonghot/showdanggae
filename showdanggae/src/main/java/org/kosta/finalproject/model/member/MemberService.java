package org.kosta.finalproject.model.member;

import java.util.HashMap;
import java.util.List;



public interface MemberService {
	
	
	//BBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBB
	MemberVO idCheck(String beforeId);
	MemberVO register(MemberVO vo);
	MemberVO login(MemberVO vo);
	MemberVO findIdByEmail(MemberVO vo);
	MemberVO findPassById(MemberVO vo, String email1, String email2);
	MemberVO updateMember(MemberVO vo);
	void withdraw(String member_id,String reason);
	MemberVO adminlogin(MemberVO vo);
	MemberListVO memberManagerList(String pageNo);
	void memberDelete(String member_id);
	
	
	//CCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCC
	
	public List<MemberVO> findMemberById(String id, String sessionId);
	public void addFollow(FollowVO fvo);
	public void deleteFollow(FollowVO vo);
	public List<FollowVO> findFollowingId(String member_id);
	public List<FollowVO> findFollowerId(String member_id);
	public List<MemberVO> onkeyupId(String searchId);
	List<FollowVO> fAlarm(String following);
	HashMap<String, String> proCount(String member_id);
	List<String> profileInterest(String member_id);
	List<String> profileInterestList();
	List<String> myinterestList(String member_id);
	MemberVO infoUpdate(MemberVO vo);

}