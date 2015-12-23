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
	/**
	 * 
	 * @Method 이름 : findMemberById
	 * @Method 설명 : right.jsp에서 검색하고자 하는 친구의 ID를 검색하는 메서드. 검색시 입력어가 포함된 모든 ID가 리스트로 출력된다.
	 * @param member_id
	 * @param sessionId
	 * @return
	 * @throws Exception
	 * @작성일 : 2015. 12. 22.
	 * @작성자 : 송슬하,박준서
	 */
	@Override
	public List<MemberVO> findMemberById(String id) {
		return sqlSessionTemplate.selectList("member.findMemberById",id);
	}
	/**
	 * 
	 * @Method 이름 : addFollow
	 * @Method 설명 : right.jsp에서 친구 ID 검색 후, 팔로잉을 하는 메서드 '+팔로우' 버튼 클릭과 동시에 친구추가가 되고
	 * 					 'v팔로잉' 버튼으로 변경된다.
	 * 		
	 * @param fvo
	 * @param request
	 * @throws Exception
	 * @작성일 : 2015. 12. 22.
	 * @작성자 : 송슬하,박준서
	 */
	@Override
	public void addFollow(FollowVO fvo) {
		System.out.println(fvo);
		sqlSessionTemplate.insert("member.addFollow", fvo);
	}
	/**
	 * 
	 * @Method 이름 : deleteFollow
	 * @Method 설명 : right.jsp에서  팔로잉한 친구를 팔로잉 취소하는 메서드 'v팔로잉' 버튼 클릭과 동시에 친구삭제가 되고
	 * 					 '+팔로우' 버튼으로 변경된다.
	 * @param vo
	 * @param request
	 * @throws Exception
	 * @작성일 : 2015. 12. 22.
	 * @작성자 : KOSTA
	 */
	@Override
	public void deleteFollow(FollowVO vo) {
		System.out.println(vo);
		sqlSessionTemplate.delete("member.deleteFollow", vo);
	}
	/**
	 * 
	 * @Method 이름 : findFollowingId
	 * @Method 설명 : right.jsp에서 팔로잉 탭을 클릭했을시 내가 팔로잉한 친구 리스트를 보여준다.
	 * @param member_id
	 * @return
	 * @throws Exception
	 * @작성일 : 2015. 12. 22.
	 * @작성자 : KOSTA
	 */
	@Override
	public List<FollowVO> findFollowingId(String member_id) {
		return sqlSessionTemplate.selectList("follow.findFollowingId", member_id);
	}
	/**
	 * 
	 * @Method 이름 : findFollowerId
	 * @Method 설명 : right.jsp에서 팔로워 탭을 클릭했을 시 나를 팔로우한 친구 리스트를 보여준다.
	 * @param member_id
	 * @return
	 * @throws Exception
	 * @작성일 : 2015. 12. 22.
	 * @작성자 : KOSTA
	 */
	@Override
	public List<FollowVO> findFollowerId(String member_id) {
		return sqlSessionTemplate.selectList("follow.findFollowerId", member_id);
	}
	/**
	 * 
	 * @Method 이름 : findIsFollowBySessionId
	 * @Method 설명 : right.jsp에서 검색하고자 하는 친구의 ID를 검색할 때 map에 로그인 한 아이디와
	 * 검색한 아이디를 넣어주어 서로의 관계를 확인한 후 로그인한 아이디와 검색한 아이디가 팔로우 관계이면 true를
	 * 아니면 false를 지정해준다.
	 * @param member_id
	 * @param sessionId
	 * @return
	 * @throws Exception
	 * @작성일 : 2015. 12. 22.
	 * @작성자 : 송슬하,박준서
	 */
	@Override
	public String findIsFollowBySessionId(HashMap<String, String> map) {
		return sqlSessionTemplate.selectOne("member.findIsFollowBySessionId",map);
	}
	/**
	 * 필요없음
	 */
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

	@Override
	public int productCount(String member_id) {
		// TODO Auto-generated method stub
		return sqlSessionTemplate.selectOne("member.productCount", member_id);
	}

}
