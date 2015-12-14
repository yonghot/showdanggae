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
	
	@Override
	public List<MemberVO> findMemberById(String member_id, String sessionId) {
		System.out.println("서비스영역"+member_id+" "+sessionId);
		ArrayList<MemberVO> list = (ArrayList<MemberVO>) memberDAO.findMemberById(member_id);
		HashMap<String, String> map = new HashMap<String, String>();
		map.put("sessionId", sessionId);
		boolean isFollow = false;
		
/*		for(int i=0;i<list.size();i++) {
			map.put("listId", list.get(i).getMember_id());
			if(memberDAO.findIsFollowBySessionId(map)!=null) {
				isFollow = true;
			} 
			if(isFollow){
				list.get(i).setIsFollow(isFollow);
				isFollow = false;
			}
		}*/
		return list;
	}

	@Override
	public void addFollow(FollowVO fvo) {
		memberDAO.addFollow(fvo);
	}
	
	@Override
	public void deleteFollow(FollowVO vo) {
		memberDAO.deleteFollow(vo);
	}
	
	@Override
	public List<FollowVO> findFollowingId(String member_id) {
		return memberDAO.findFollowingId(member_id);
	}

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
	

}
