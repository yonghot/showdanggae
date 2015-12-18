package org.kosta.finalproject.model.member;

import java.util.HashMap;
import java.util.List;

import javax.annotation.Resource;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class MemberDAOImpl implements MemberDAO {
	
	@Resource
	private SqlSessionTemplate sqlSessionTemplate;
	
	//BBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBB
	@Override
	public MemberVO idCheck(String beforeId) {
		return  sqlSessionTemplate.selectOne("member.idCheck", beforeId);
	}

	@Override
	public MemberVO register(MemberVO vo) {
		sqlSessionTemplate.insert("member.register", vo);
		return sqlSessionTemplate.selectOne("member.idCheck", vo.getMember_id());
	}

	@Override
	public MemberVO login(MemberVO vo) {
		
		return sqlSessionTemplate.selectOne("member.login", vo);
	}
	
	@Override
	public MemberVO findIdByEmail(MemberVO vo){
		return sqlSessionTemplate.selectOne("member.findIdByBirth",vo);	
	}
	
	@Override
	public MemberVO findPassById(MemberVO vo){
		return sqlSessionTemplate.selectOne("member.findPassById",vo);
	}

	@Override
	public void updateMember(MemberVO vo) {
		sqlSessionTemplate.update("member.updateMember", vo);
	}

	@Override
	public MemberVO updateOk(String member_id) {
		
		return sqlSessionTemplate.selectOne("member.updateOk", member_id);
	}

	@Override
	public void withdraw(String member_id) {
		
		sqlSessionTemplate.delete("member.withdraw", member_id);
		
	}

	@Override
	public MemberVO adminlogin(MemberVO vo) {

		return sqlSessionTemplate.selectOne("member.adminlogin",vo);
	}

	@Override
	public List<MemberVO> memberManagerList(int pn) {
		// TODO Auto-generated method stub
		return sqlSessionTemplate.selectList("member.memberManagerList",pn);
	}

	@Override
	public void memberDelete(String member_id) {
		//ȸ������
		 sqlSessionTemplate.delete("member.memberDelete", member_id);
	}

	@Override
	public int getCount() {
		return sqlSessionTemplate.selectOne("member.getCount");
	}
	
	
	//CCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCC
	
	@Override
	public List<MemberVO> findMemberById(String id) {
		System.out.println("dao 영역"+id);
		return sqlSessionTemplate.selectList("member.findMemberById",id);
	}
	
	@Override
	public void addFollow(FollowVO fvo) {
		System.out.println(fvo);
		sqlSessionTemplate.insert("member.addFollow", fvo);
	}

	@Override
	public void deleteFollow(FollowVO vo) {
		System.out.println(vo);
		sqlSessionTemplate.delete("member.deleteFollow", vo);
	}
	
	@Override
	public List<FollowVO> findFollowingId(String member_id) {
		return sqlSessionTemplate.selectList("follow.findFollowingId", member_id);
	}

	@Override
	public List<FollowVO> findFollowerId(String member_id) {
		return sqlSessionTemplate.selectList("follow.findFollowerId", member_id);
	}

	@Override
	public String findIsFollowBySessionId(HashMap<String, String> map) {
		return sqlSessionTemplate.selectOne("member.findIsFollowBySessionId",map);
	}

	@Override
	public List<MemberVO> onkeyupId(String searchId) {
		return sqlSessionTemplate.selectList("member.onkeyupId", searchId);
	}

	@Override
	public List<FollowVO> fAlarm(String following) {
		return sqlSessionTemplate.selectList("member.fAlarm", following);
	}

	@Override
	public int followerCount(String member_id) {
		//나를 받아보는 사람
		String following=member_id;
		int followerCount1=sqlSessionTemplate.selectOne("member.followerCount", following);
		//String followerCount = String.valueOf(followerCount1);
		return followerCount1;
	}

	@Override
	public int followingCount(String member_id) {
		String follower=member_id;
		int followingCount1=sqlSessionTemplate.selectOne("member.followingCount", follower);
		/*String followerCount = String.valueOf(followingCount1);
		System.out.println(followerCount);*/
		return followingCount1;
	}

	@Override
	public List<String> profileInterest(String member_id) {
		return sqlSessionTemplate.selectList("member.profileInterest", member_id);
	}

	@Override
	public List<String> profileInterestList(String member_id) {
		
		return sqlSessionTemplate.selectList("member.profileInterestList",member_id);
	}

	@Override
	public List<String> myinterestList(String member_id) {
		return sqlSessionTemplate.selectList("member.myinterestList", member_id);
	}

	@Override
	public MemberVO infoUpdate(MemberVO vo) {
		 sqlSessionTemplate.update("member.infoUpdate", vo);
		
		return  sqlSessionTemplate.selectOne("member.sessionConnect", vo.getMember_id());
	}

	@Override
	public void interestAdd(HashMap<String, String> add) {
		// 관심사 추가
		sqlSessionTemplate.insert("member.interestAdd", add);
	}

	@Override
	public void interestDel(HashMap<String, String> del) {
		sqlSessionTemplate.delete("member.interestDel", del);
	}

}
