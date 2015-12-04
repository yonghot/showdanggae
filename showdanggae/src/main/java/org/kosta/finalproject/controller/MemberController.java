package org.kosta.finalproject.controller;

import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.kosta.finalproject.model.member.FollowVO;
import org.kosta.finalproject.model.member.MemberListVO;
import org.kosta.finalproject.model.member.MemberService;
import org.kosta.finalproject.model.member.MemberVO;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class MemberController {

	@Resource
	private MemberService memberService;
	
	@RequestMapping("{viewId}.do")
	public String showTilesView(@PathVariable String viewId) {
		System.out.println(viewId);
		return viewId;
	}
	
	
	
	//BBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBB
	
	
	@RequestMapping("idCheck.do")
	public ModelAndView idCheck(HttpServletRequest request,
			HttpServletResponse response, String member_Id) {
		MemberVO vo = memberService.idCheck(member_Id);
		if (vo != null) {
			return new ModelAndView("ajaxView", "vo", vo);
		} else {
			return new ModelAndView("ajaxView", "vo", null);
		}

	}
	@RequestMapping("register.do")
	public ModelAndView register(MemberVO vo){
		MemberVO insertVO=memberService.register(vo);
		return new ModelAndView("redirect:registerF5.do?member_name=" + insertVO.getMember_name());
	}
	
	@RequestMapping("registerF5.do")
	public ModelAndView registerF5(String member_name) {
	
		return new ModelAndView("member_registerokview","member_name",member_name);
	}
	
	@RequestMapping("registerview.do")
	public String registerview() {
		//ȸ������â form ���� �̵�
		return "member_registerview";
	}
	
	
	@RequestMapping("login.do")
	public ModelAndView login(HttpServletRequest request, MemberVO vo){
		
		if(vo.getMember_id().equals("admin")){
			MemberVO admin=memberService.adminlogin(vo);
			if(admin!=null){		
				HttpSession session = request.getSession(true);			
				session.setAttribute("managerlogin", admin);
				if(session.getAttribute("mvo")!=null) {
					session.setAttribute("mvo", null);
				}
				return new ModelAndView("home");
			}else{
				return new ModelAndView("member_loginfail");
			}
		}
		
		MemberVO mvo = memberService.login(vo);
		if(mvo!=null){ //���̵� ������
			HttpSession session = request.getSession(true);	
			session.setAttribute("mvo", mvo);
			if(session.getAttribute("managerlogin")!=null) {
				session.setAttribute("managerlogin", null);
			}
			return new ModelAndView("home");
		}else{	
			//�α��� ����
			return new ModelAndView("member_loginfail");
		}
	}
	
	@RequestMapping("logout.do")
	public ModelAndView logout(HttpServletRequest request, HttpServletResponse repuest){
		HttpSession session = request.getSession(false);
		if(session!=null){
			session.invalidate();
		}
		return new ModelAndView("redirect:home.do");
	}
	
	@RequestMapping("registercancel.do")
	public String registercancel(){
		//ȸ������ ���
		return "home";		
	}
	
	@RequestMapping("updatecancel.do")
	public String updatecancel(){
		//�������� ���
		return "home";		
	}
	
	@RequestMapping("update_password.do")
	public String update_password(HttpServletRequest request,HttpServletResponse response){
		return "member_update_password";
	}
	
	@RequestMapping("myinfo_view.do")
	public String myinfo_view(HttpServletRequest request,HttpServletResponse response){

		HttpSession session = request.getSession(false);
		session.getAttribute("memberOK");
		System.out.println(session.getAttribute("memberOK"));

		return "member_myinfo_view";
	} 
	
	
	@RequestMapping("updateMember.do")
	public ModelAndView update(HttpServletRequest request,HttpServletResponse response,MemberVO vo){
		HttpSession session = request.getSession(false);

		if(session!=null){
			MemberVO member=memberService.updateMember(vo);
			session.invalidate();
			HttpSession session1 = request.getSession(true);
			session1.setAttribute("memberOK", member);
			return new ModelAndView("member_memberUpdateOk","memberOK", member);	
		}
		return new ModelAndView("redirect:home.do");
	}
	
	//ȸ��Ż�� form ����
	@RequestMapping("withdrawForm.do")
	public String withdrawForm(){
		return "member_withdraw";	
	}
	
	//ȸ��Ż�� ����
	@RequestMapping("withdraw.do")
	public String withdraw(HttpServletRequest request,MemberVO vo){
		String reason=request.getParameter("reason");
		memberService.withdraw(vo.getMember_id(),reason);
		
		HttpSession session = request.getSession(false);
		if(session!=null){
			session.invalidate();
		}
		
		return "member_withdrawOk";
	}
	
	////////////////////////////////////////////////////////////////////////DSF45ASDFAWE6 여기!!
	@RequestMapping("memberManagerForm.do")
	public ModelAndView memberManagerForm(HttpServletRequest request,HttpServletResponse response){
		//ȸ������ form����
/*		String pageNo=request.getParameter("pageNo");
		ListVO list=noticeService.noticeList(pageNo);*/
		String pageNo=request.getParameter("pageNo");
		MemberListVO mvolist=memberService.memberManagerList(pageNo);
		System.out.println(mvolist);
	
		return new ModelAndView("member_memberManagerForm","memberList",mvolist);
	}
	
	@RequestMapping("memberDelete.do")
	public ModelAndView memberDelete(HttpServletRequest request,HttpServletResponse response){
		String member_Id=request.getParameter("member_Id");
		memberService.memberDelete(member_Id);
		String pageNo=request.getParameter("pageNo");
		MemberListVO mvolist = memberService.memberManagerList(pageNo);
		System.out.println(mvolist);

		return new ModelAndView("member_memberManagerDeleteOk");	
	}
	
	
	//CCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCC
	
	@RequestMapping("findMemberById.do")
	public ModelAndView findMemberById(String member_id, String sessionId) throws Exception{
		return new ModelAndView("follow_findbyid", "mvoList", memberService.findMemberById(member_id, sessionId));
	}

	@RequestMapping("add.do")
	public void addFollow(FollowVO fvo) throws Exception {
		memberService.addFollow(fvo);
	}
	
	@RequestMapping("delete.do")
	public void deleteFollow(FollowVO vo) throws Exception {
		memberService.deleteFollow(vo);
	}
	
	@RequestMapping("findFollowingId.do")
	public ModelAndView findFollowingId(String member_id) throws Exception {
		List<FollowVO> list = memberService.findFollowingId(member_id);
		return new ModelAndView("follow_followingid", "followingList", list);
	}

	@RequestMapping("findFollowerId.do")
	public ModelAndView findFollowerId(String member_id) throws Exception {
		List<FollowVO> list = memberService.findFollowerId(member_id);
		return new ModelAndView("follow_followerid", "followerList", list);
	}
	
	@RequestMapping("onkeyupId.do")
	public ModelAndView onkeyupId(String searchId) throws Exception{
		//System.out.println(searchId);
		return new ModelAndView("ajaxView", "svoList", memberService.onkeyupId(searchId));
	}

}
