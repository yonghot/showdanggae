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
	
	/**
	 * 
	 * @Method Name  : idCheck
	 * @작성일   : 2015. 12. 23. 
	 * @작성자   : 유서정
	 * @변경이력  :
	 * @Method 설명 : 회원가입창에서 입력받은 member_id 에 대한 중복 여부 검색
	 * @param beforeId
	 * @return
	 */
	@Override
	public MemberVO idCheck(String beforeId) {
		return  sqlSessionTemplate.selectOne("member.idCheck", beforeId);
	}

	/**
	 * 
	 * @Method Name  : register
	 * @작성일   : 2015. 12. 23. 
	 * @작성자   : 유서정
	 * @변경이력  :
	 * @Method 설명 :회원가입창에서 입력받은 회원정보를 저장
	 * 							(db에 저장될때는 email Id와domain합쳐진 형태로 저장되고)
	 * 							(vo에서는 email Id 와 Domain이 분류된 형태로 저장된다)
	 * @param vo
	 * @return
	 */
	@Override
	public MemberVO register(MemberVO vo) {
		sqlSessionTemplate.insert("member.register", vo);
		return sqlSessionTemplate.selectOne("member.idCheck", vo.getMember_id());
	}

/**
 * 
 * @Method Name  : login
 * @작성일   : 2015. 12. 23. 
 * @작성자   : 유서정
 * @변경이력  :
 * @Method 설명 : 회원이 입력한 아이디와 비밀번호와 일치하는 회원을 검색한다
 * @param vo
 * @return
 */
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

	/**
	 * 
	 * @Method Name  : updateMember
	 * @작성일   : 2015. 12. 23. 
	 * @작성자   : 유서정
	 * @변경이력  :
	 * @Method 설명 :회원이 정보수정을 요청하면 수정된 정보를 저장
	 * @param vo
	 */
	@Override
	public void updateMember(MemberVO vo) {
		sqlSessionTemplate.update("member.updateMember", vo);
	}

	/**
	 * 
	 * @Method Name  : updateOk
	 * @작성일   : 2015. 12. 23. 
	 * @작성자   : 유서정
	 * @변경이력  :
	 * @Method 설명 : 회원이 정보를 수정 한 후 수정된 내용을 검색
	 * @param member_id
	 * @return
	 */
	@Override
	public MemberVO updateOk(String member_id) {
		return sqlSessionTemplate.selectOne("member.updateOk", member_id);
	}

	/**
	 * 
	 * @Method Name  : withdraw
	 * @작성일   : 2015. 12. 23. 
	 * @작성자   : 유서정
	 * @변경이력  :
	 * @Method 설명 : 회원아이디를 검색해 memberTable에서 탈퇴처리 (삭제)
	 * @param member_id
	 */
	@Override
	public void withdraw(String member_id) {
		sqlSessionTemplate.delete("member.withdraw", member_id);
		
	}

	/**
	 * 
	 * @Method Name  : adminlogin
	 * @작성일   : 2015. 12. 23. 
	 * @작성자   : 유서정
	 * @변경이력  :
	 * @Method 설명 : 관리자의 아이디와 비밀번호로 일치하는 내용을 검색
	 * @param vo
	 * @return
	 */
	@Override
	public MemberVO adminlogin(MemberVO vo) {
		return sqlSessionTemplate.selectOne("member.adminlogin",vo);
	}

	/**
	 * 
	 * @Method Name  : memberManagerList
	 * @작성일   : 2015. 12. 23. 
	 * @작성자   : 유서정
	 * @변경이력  :
	 * @Method 설명 : 페이지에 해당하는 회원들의 정보 리스트를 검색
	 * @param pn
	 * @return
	 */
	@Override
	public List<MemberVO> memberManagerList(int pn) {
		return sqlSessionTemplate.selectList("member.memberManagerList",pn);
	}

	/**
	 * 
	 * @Method Name  : memberDelete
	 * @작성일   : 2015. 12. 23. 
	 * @작성자   : 유서정
	 * @변경이력  :
	 * @Method 설명 : 회원탈퇴 처리할 아이디를 삭제한다
	 * @param member_id
	 */
	@Override
	public void memberDelete(String member_id) {
		 sqlSessionTemplate.delete("member.memberDelete", member_id);
	}

	/**
	 * 
	 * @Method Name  : getCount
	 * @작성일   : 2015. 12. 23. 
	 * @작성자   : 유서정
	 * @변경이력  :
	 * @Method 설명 : 가입한 회원들 수 검색해 memberTable에서 삭제
	 * @return
	 */
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

	/**
	 * 
	 * @Method Name  : fAlarm
	 * @작성일   : 2015. 12. 23. 
	 * @작성자   : 유서정
	 * @변경이력  :
	 * @Method 설명 : 현재시간으로부터 24시간 전까지 로그인한 회원을 다른 회원이 팔로잉 한 시간과 회원을 검색
	 * @param following
	 * @return
	 */
	@Override
	public List<FollowVO> fAlarm(String following) {
		return sqlSessionTemplate.selectList("member.fAlarm", following);
	}

	/**
	 * 
	 * @Method Name  : followerCount
	 * @작성일   : 2015. 12. 23. 
	 * @작성자   : 유서정
	 * @변경이력  :
	 * @Method 설명 : 로그인한 회원의 글을 받아보는 다른 회원들의 수를 검색
	 * @param member_id
	 * @return
	 */
	@Override
	public int followerCount(String member_id) {
		String following=member_id;
		int followerCount1=sqlSessionTemplate.selectOne("member.followerCount", following);
		return followerCount1;
	}

	/**
	 * 
	 * @Method Name  : followingCount
	 * @작성일   : 2015. 12. 23. 
	 * @작성자   : 유서정
	 * @변경이력  :
	 * @Method 설명 :로그인한 회원이 받아보는 다른회원들의 수를 검색
	 * @param member_id
	 * @return
	 */
	@Override
	public int followingCount(String member_id) {
		String follower=member_id;
		int followingCount1=sqlSessionTemplate.selectOne("member.followingCount", follower);
		return followingCount1;
	}

	/**
	 * 
	 * @Method Name  : profileInterest
	 * @작성일   : 2015. 12. 23. 
	 * @작성자   : 유서정
	 * @변경이력  :
	 * @Method 설명 :회원이 저장한 자신의 관심사목록을 검색
	 * @param member_id
	 * @return
	 */
	@Override
	public List<String> profileInterest(String member_id) {
		return sqlSessionTemplate.selectList("member.profileInterest", member_id);
	}

	/**
	 * 
	 * @Method Name  : profileInterestList
	 * @작성일   : 2015. 12. 23. 
	 * @작성자   : 유서정
	 * @변경이력  :
	 * @Method 설명 : 회원이 선택한 관심사를 제외한 쇼당개가 제공하는 관심사를 검색
	 * @param member_id
	 * @return
	 */
	@Override
	public List<String> profileInterestList(String member_id) {
		return sqlSessionTemplate.selectList("member.profileInterestList",member_id);
	}

	/**
	 * 
	 * @Method Name  : myinterestList
	 * @작성일   : 2015. 12. 23. 
	 * @작성자   : 유서정
	 * @변경이력  :
	 * @Method 설명 :회원이 선택하여 추가한 관심사 리스트를 검색
	 * @param member_id
	 * @return
	 */
	@Override
	public List<String> myinterestList(String member_id) {
		return sqlSessionTemplate.selectList("member.myinterestList", member_id);
	}

	/**
	 * 
	 * @Method Name  : infoUpdate
	 * @작성일   : 2015. 12. 23. 
	 * @작성자   : 유서정
	 * @변경이력  :
	 * @Method 설명 : 회원이 수정한 회원정보를 수정하고, 저장된 정보를
	 * 				 session에 다시 연결해 주기위해
	 * 				 해당 id에 일치하는 회원정보를 다시 검색한다
	 * @param vo
	 * @return
	 */
	@Override
	public MemberVO infoUpdate(MemberVO vo) {
		sqlSessionTemplate.update("member.infoUpdate", vo);
	return sqlSessionTemplate.selectOne("member.sessionConnect", vo.getMember_id());
	}

	/**
	 * 
	 * @Method Name  : interestAdd
	 * @작성일   : 2015. 12. 23. 
	 * @작성자   : 유서정
	 * @변경이력  :
	 * @Method 설명 : 회원이 관심사를 추가했을시 수정
	 * @param add
	 */
	@Override
	public void interestAdd(HashMap<String, String> add) {
		sqlSessionTemplate.insert("member.interestAdd", add);
	}

	/**
	 * 
	 * @Method Name  : interestDel
	 * @작성일   : 2015. 12. 23. 
	 * @작성자   : 유서정
	 * @변경이력  :
	 * @Method 설명 : 회원이 선택했던 관심사를 다시 취소처리
	 * @param del
	 */
	@Override
	public void interestDel(HashMap<String, String> del) {
		sqlSessionTemplate.delete("member.interestDel", del);
	}

	/**
	 * 
	 * @Method Name  : productCount
	 * @작성일   : 2015. 12. 23. 
	 * @작성자   : 유서정
	 * @변경이력  :
	 * @Method 설명 : 회원이 올린 게시물 수를 검색
	 * @param member_id
	 * @return
	 */
	@Override
	public int productCount(String member_id) {
		return sqlSessionTemplate.selectOne("member.productCount", member_id);
	}

}
