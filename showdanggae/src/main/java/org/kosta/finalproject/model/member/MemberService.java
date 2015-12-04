package org.kosta.finalproject.model.member;

import java.util.List;



public interface MemberService {
	
	
	//BBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBB
	MemberVO idCheck(String beforeId);
	MemberVO register(MemberVO vo);
	MemberVO login(MemberVO vo);
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
}