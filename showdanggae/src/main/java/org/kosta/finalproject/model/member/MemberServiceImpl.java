package org.kosta.finalproject.model.member;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;


@Service
public class MemberServiceImpl implements MemberService {
	@Resource
	private MemberDAO memberDAO;
	
	//BBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBB

	
	/**
	 * @Method Name  : idCheck
	 * @작성일   : 2015. 12. 23. 
	 * @작성자   : 유서정
	 * @변경이력  :
	 * @Method 설명 : 회원가입창에서 입력받은 member_id 에 대한 중복 여부를 알려준다
	 * @param beforeId
	 * @return
	 */
	@Override
	public MemberVO idCheck(String beforeId) {
		return memberDAO.idCheck(beforeId);
	}

	/**
	 * 
	 * @Method Name  : register
	 * @작성일   : 2015. 12. 23. 
	 * @작성자   : 유서정
	 * @변경이력  :
	 * @Method 설명 : 회원가입창에서 입력받은 회원정보를 저장
	 * 							(db에 저장될때는 email Id와domain합쳐진 형태로 저장되고)
	 * 							(vo에서는 email Id 와 Domain이 분류된 형태로 저장된다)
	 * @param vo
	 * @return
	 */
	@Override
	public MemberVO register(MemberVO vo) {
		MemberVO insertVO=memberDAO.register(vo);	
		insertVO.setEmail_id(insertVO.getEmail().split("@")[0]);
		insertVO.setEmail_domain(insertVO.getEmail().split("@")[1]);	
		return insertVO;	
	}
	

	@Override
	public MemberVO login(MemberVO vo) {
			MemberVO loginVO=memberDAO.login(vo);
			if(loginVO!=null){		
				loginVO.setEmail_id(loginVO.getEmail().split("@")[0]);
				loginVO.setEmail_domain(loginVO.getEmail().split("@")[1]);
			}
		return loginVO;
	}
	
	@Override
	public MemberVO findIdByEmail(MemberVO vo){
		String email1=vo.getEmail_id();
		String email2=vo.getEmail_domain();
		String email = email1+"@"+email2;
		vo.setEmail(email);
		return memberDAO.findIdByEmail(vo);
	}
	
	@Override
	public MemberVO findPassById(MemberVO vo, String email1, String email2){
		String email = email1+"@"+email2;
		vo.setEmail(email);
		return memberDAO.findPassById(vo);
	}
/**
 * 
 * @Method Name  : updateMember
 * @작성일   : 2015. 12. 23. 
 * @작성자   : 유서정
 * @변경이력  :
 * @Method 설명 :회원이 정보수정을 요청하면 수정된 정보를 다시 DB에 저장한다
 * @param vo
 * @return
 */
	@Override
	public MemberVO updateMember(MemberVO vo) {
		memberDAO.updateMember(vo); 
		MemberVO member=memberDAO.updateOk(vo.getMember_id());
		member.setEmail_id(member.getEmail().split("@")[0]);
		member.setEmail_domain(member.getEmail().split("@")[1]);
	return member;
	}

/**
 * 
 * @Method Name  : withdraw
 * @작성일   : 2015. 12. 23. 
 * @작성자   : 유서정
 * @변경이력  :
 * @Method 설명 :회원탈퇴 사유와 아이디를 입력받아 db에 저장된 회원정보를 삭제한 후
 * 							탈퇴사유는 txt 파일에 저장시킨다
 * @param member_id
 * @param reason
 */
	@Override
	public void withdraw(String member_id,String reason) {
	memberDAO.withdraw(member_id);
	String withdrawPath="C:\\java-kosta\\WAS\\project-tomcat\\webapps\\showdanggae\\withdraw\\withdrawreason.txt";
	try {
		FileWriter fw = new FileWriter(withdrawPath,true);
		PrintWriter pw=new PrintWriter(fw);
		pw.println(member_id + "탈퇴사유 :");
		pw.println(reason);
		pw.close();

	} catch (IOException e) {
		e.printStackTrace();
	}
	
	}

/**
 * 
 * @Method Name  : adminlogin
 * @작성일   : 2015. 12. 23. 
 * @작성자   : 유서정
 * @변경이력  :
 * @Method 설명 : controller에서 관리자 아이디가 들어오면 관리자의 아이디와 비밀번호가 일치하는 정보를 담는다
 * @param vo
 * @return
 */
	@Override
	public MemberVO adminlogin(MemberVO vo) {
		MemberVO adminVO=memberDAO.adminlogin(vo);
		if(adminVO!=null){		
			adminVO.setEmail_id(adminVO.getEmail().split("@")[0]);
			adminVO.setEmail_domain(adminVO.getEmail().split("@")[1]);
		}	
		return adminVO;
	}

/**
 * 
 * @Method Name  : memberManagerList
 * @작성일   : 2015. 12. 23. 
 * @작성자   : 유서정
 * @변경이력  :
 * @Method 설명 : 관리자가 회원관리를 편리 하게 할 수 있도록 쇼당개에 가입한 회원 정보를 리스트로 보여주고
 * 							 일정 리스트 갯수가 초과하면 페이징을 생성해 한 페이지당 보여줄 회원 정보 리스트를 반환한다 
 * @param pageNo
 * @return
 */
	@Override
	public MemberListVO memberManagerList(String pageNo) {
		MemberPagingBean mpagingBean=new MemberPagingBean();
		ArrayList<MemberVO> memlist =new ArrayList<MemberVO>();
		MemberListVO mvolist=new MemberListVO(memlist,mpagingBean);
	
		int pn=1;
		if(pageNo!=null){
			//선택한 pageNo 값이 들어있으면 pageNo를 int형으로 변환해서 해당하는 리스트들을 검색한다
			pn=Integer.parseInt(pageNo);		
			memlist=(ArrayList<MemberVO>) memberDAO.memberManagerList(pn);

			int count=memberDAO.getCount(); 
			mpagingBean=new MemberPagingBean(count,pn); 
			mvolist=new MemberListVO(memlist,mpagingBean);
		}else{			
			//선택한 pageNo 값이 없으면 보여줄 페이지 번호를 임의로 지정한 pn값 1로 검색한다
			int count=memberDAO.getCount();
			memlist=(ArrayList<MemberVO>) memberDAO.memberManagerList(pn);
			mpagingBean=new MemberPagingBean(count,pn);
			mvolist=new MemberListVO(memlist,mpagingBean);
		}	
		return mvolist;
	}

/**
 * 
 * @Method Name  : memberDelete
 * @작성일   : 2015. 12. 23. 
 * @작성자   : 유서정
 * @변경이력  :
 * @Method 설명 : 관리자가 다른회원들에게서 신고받은 회원을 탈퇴시킨다
 * @param member_id
 */
	@Override
	public void memberDelete(String member_id) {
		memberDAO.memberDelete(member_id);
		
	}
	
	//CCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCC
	/**
	 * 
	 */
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
	public List<MemberVO> findMemberById(String member_id, String sessionId) {
		ArrayList<MemberVO> list = (ArrayList<MemberVO>) memberDAO.findMemberById(member_id);
		HashMap<String, String> map = new HashMap<String, String>();
		map.put("sessionId", sessionId);
		boolean isFollow = false;
		
		for(int i=0;i<list.size();i++) {
			map.put("listId", list.get(i).getMember_id());
			if(memberDAO.findIsFollowBySessionId(map)!=null) {
				isFollow = true;
			} 
			if(isFollow){
				list.get(i).setIsFollow(isFollow);
				isFollow = false;
			}else{
				list.get(i).setIsFollow(false);
			}
		}
		return list;
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
		memberDAO.addFollow(fvo);
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
		memberDAO.deleteFollow(vo);
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
		return memberDAO.findFollowingId(member_id);
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
		return memberDAO.findFollowerId(member_id);
	}
	
	@Override
	public List<MemberVO> onkeyupId(String searchId) {
		return memberDAO.onkeyupId(searchId);
	}


	/**
	 * 
	 * @Method Name  : fAlarm
	 * @작성일   : 2015. 12. 23. 
	 * @작성자   : 유서정
	 * @변경이력  :
	 * @Method 설명 : 현재시간부터 24시간 전까지 나를 팔로잉한 사람 목록을 알려준다
	 * @param following
	 * @return
	 */
	@Override
	public List<FollowVO> fAlarm(String following) {
		return memberDAO.fAlarm(following);
	}

/**
 * 
 * @Method Name  : proCount
 * @작성일   : 2015. 12. 23. 
 * @작성자   : 유서정
 * @변경이력  :
 * @Method 설명 : 회원 자신이 올린 게시물 수, 회원글을 받아보는 팔로워 수, 회원이
 * 				  다른 회원을 받아보는 팔로잉 수를 각각 HashMap으로 저장한다
 * @param member_id
 * @return
 */
	@Override
	public HashMap<String, String> proCount(String member_id) {
		HashMap<String, String> proInfo =new  HashMap<String, String>();
	
		int followerCount1=memberDAO.followerCount(member_id);
		int followingCount1=memberDAO.followingCount(member_id);
		int productCount1=memberDAO.productCount(member_id);
		String followerCount = String.valueOf(followerCount1);
		String followingCount = String.valueOf(followingCount1);
		String productCount = String.valueOf(productCount1);
	
		proInfo.put("followerCount", followerCount);
		proInfo.put("followingCount", followingCount);
		proInfo.put("productCount", productCount);
		return proInfo;
	}


	/**
	 * 
	 * @Method Name  : profileInterest
	 * @작성일   : 2015. 12. 23. 
	 * @작성자   : 유서정
	 * @변경이력  :
	 * @Method 설명 : 회원이 저장한 자신의 관심사목록 반환한다
	 * @param member_id
	 * @return
	 */
	@Override			
	public List<String> profileInterest(String member_id) {	
		return memberDAO.profileInterest(member_id);
	}

/**
 * 
 * @Method Name  : profileInterestList
 * @작성일   : 2015. 12. 23. 
 * @작성자   : 유서정
 * @변경이력  :
 * @Method 설명 : 회원이 선택한 관심사를 제외한 쇼당개가 제공하는 관심사를 반환
 * @param member_id
 * @return
 */
	@Override
	public List<String> profileInterestList(String member_id) {
		return memberDAO.profileInterestList(member_id);
	}

/**
 * 
 * @Method Name  : myinterestList
 * @작성일   : 2015. 12. 23. 
 * @작성자   : 유서정
 * @변경이력  :
 * @Method 설명 : 회원이 선택한 관심사리스트를 반환
 * @param member_id
 * @return
 */
	@Override
	public List<String> myinterestList(String member_id) {
		return memberDAO.myinterestList(member_id);
	}

/**
 * 
 * @Method Name  : infoUpdate
 * @작성일   : 2015. 12. 23. 
 * @작성자   : 유서정
 * @변경이력  :
 * @Method 설명 :template의 왼쪽에 나타나는 나의 프로필 정보(관심사 , 내 소개)를 수정한다
 * @param vo
 * @return
 */
	@Override
	public MemberVO infoUpdate(MemberVO vo) {
		return memberDAO.infoUpdate(vo);
	}

/**
 * 
 * @Method Name  : interestAdd
 * @작성일   : 2015. 12. 23. 
 * @작성자   : 유서정
 * @변경이력  :
 * @Method 설명 :profile 수정시 관심사를 추가 할 수 있다
 * @param member_id
 * @param category
 */
	@Override
	public void interestAdd(String member_id, String category) {
		HashMap<String, String> add= new HashMap<String, String>();
		add.put("member_id", member_id);
		add.put("category", category);
		memberDAO.interestAdd(add);
	}

/**
 * 
 * @Method Name  : interestDel
 * @작성일   : 2015. 12. 23. 
 * @작성자   : 유서정
 * @변경이력  :
 * @Method 설명 : profile 수정시 관심사를 삭제 할 수 있다
 * @param member_id
 * @param category
 */
	@Override
	public void interestDel(String member_id, String category) {
		HashMap<String, String> del= new HashMap<String, String>();
		del.put("member_id", member_id);
		del.put("category", category);
		memberDAO.interestDel(del);
		
	}
	

}
