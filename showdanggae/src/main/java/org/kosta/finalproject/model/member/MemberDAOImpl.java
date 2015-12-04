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
	public void updateMember(MemberVO vo) {
		sqlSessionTemplate.update("member.updateMember", vo);
	}

	@Override
	public MemberVO updateOk(String member_Id) {
		
		return sqlSessionTemplate.selectOne("member.updateOk", member_Id);
	}

	@Override
	public void withdraw(String member_Id) {
		sqlSessionTemplate.delete("member.withdraw", member_Id);
	}

	@Override
	public MemberVO adminlogin(MemberVO vo) {

		return sqlSessionTemplate.selectOne("member.adminlogin",vo);
	}

	@Override
	public List<MemberVO> memberManagerList(int pn) {
		return sqlSessionTemplate.selectList("member.memberManagerList",pn);
	}

	@Override
	public void memberDelete(String member_Id) {
		//ȸ������
		 sqlSessionTemplate.delete("member.memberDelete", member_Id);
	}

	@Override
	public int getCount() {
		return sqlSessionTemplate.selectOne("member.getCount");
	}
	
	
	
	//CCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCC
	
	@Override
	public List<MemberVO> findMemberById(String id) {
		return sqlSessionTemplate.selectList("member.findMemberById",id);
	}
	
	@Override
	public void addFollow(FollowVO fvo) {
		System.out.println(fvo);
		sqlSessionTemplate.insert("member.addFollow", fvo);
	}

	@Override
	public void deleteFollow(FollowVO vo) {
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

}
