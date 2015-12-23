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

	@Override
	public MemberVO idCheck(String beforeId) {
		return memberDAO.idCheck(beforeId);
	}


	@Override
	public MemberVO register(MemberVO vo) {
		MemberVO insertVO=memberDAO.register(vo);	
		//email_id 와 domain은 쓰지 않음
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
		//MemberVO findIdByEmail(MemberVO vo);
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

	@Override
	public MemberVO updateMember(MemberVO vo) {
		//update�Ҷ�
		memberDAO.updateMember(vo); 
		MemberVO member=memberDAO.updateOk(vo.getMember_id());
		
		member.setEmail_id(member.getEmail().split("@")[0]);
		member.setEmail_domain(member.getEmail().split("@")[1]);
		
		
		return member;
	}


	@Override
	public void withdraw(String member_id,String reason) {
	memberDAO.withdraw(member_id);

	String path="C:\\java-kosta\\finallproject\\finalproject\\src\\main\\webapp\\withdraw\\withdrawreason.txt";
						
	try {
		FileWriter fw = new FileWriter(path,true);
		PrintWriter pw=new PrintWriter(fw);
		pw.println(member_id + "탈퇴사유 :");
		pw.println(reason);
		pw.close();

	} catch (IOException e) {
		e.printStackTrace();
	}
	
	}

	//������ �α���
	@Override
	public MemberVO adminlogin(MemberVO vo) {
		MemberVO adminVO=memberDAO.adminlogin(vo);//�������
		if(adminVO!=null){		
			adminVO.setEmail_id(adminVO.getEmail().split("@")[0]);
			adminVO.setEmail_domain(adminVO.getEmail().split("@")[1]);
		}
		
		return adminVO;
	}


	@Override
	public MemberListVO memberManagerList(String pageNo) {

		MemberPagingBean mpagingBean=new MemberPagingBean();
		ArrayList<MemberVO> memlist =new ArrayList<MemberVO>();
		MemberListVO mvolist=new MemberListVO(memlist,mpagingBean);
		//메세지들이랑 페이징빈
		
		int pn=1;
		if(pageNo!=null){
			pn=Integer.parseInt(pageNo);
			
			pn=Integer.parseInt(pageNo);
			memlist=(ArrayList<MemberVO>) memberDAO.memberManagerList(pn);
			
			int count=memberDAO.getCount(); 
	
			mpagingBean=new MemberPagingBean(count,pn); //total nowpage
			mvolist=new MemberListVO(memlist,mpagingBean);
		}else{	//������ �ѹ��� ������
			
			int count=memberDAO.getCount();
			memlist=(ArrayList<MemberVO>) memberDAO.memberManagerList(pn);
			mpagingBean=new MemberPagingBean(count,pn);//26 1
			mvolist=new MemberListVO(memlist,mpagingBean);
		}
		
		return mvolist;
	}

//ȸ������
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


	@Override
	public List<FollowVO> fAlarm(String following) {
		
		return memberDAO.fAlarm(following);
	}


	@Override
	public HashMap<String, String> proCount(String member_id) {
		HashMap<String, String> proInfo =new  HashMap<String, String>();
		//게시물 수 팔로잉 팔로워 명 수 구하기 + 나의 관심사
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


	@Override
	public List<String> profileInterest(String member_id) {
		
		return memberDAO.profileInterest(member_id);
	}


	@Override
	public List<String> profileInterestList(String member_id) {
		
		return memberDAO.profileInterestList(member_id);
	}


	@Override
	public List<String> myinterestList(String member_id) {
		
		return memberDAO.myinterestList(member_id);
	}


	@Override
	public MemberVO infoUpdate(MemberVO vo) {
		return memberDAO.infoUpdate(vo);
	}


	@Override
	public void interestAdd(String member_id, String category) {
		HashMap<String, String> add= new HashMap<String, String>();
		add.put("member_id", member_id);
		add.put("category", category);
		memberDAO.interestAdd(add);
	}


	@Override
	public void interestDel(String member_id, String category) {
		HashMap<String, String> del= new HashMap<String, String>();
		del.put("member_id", member_id);
		del.put("category", category);
		memberDAO.interestDel(del);
		
	}
	

}
