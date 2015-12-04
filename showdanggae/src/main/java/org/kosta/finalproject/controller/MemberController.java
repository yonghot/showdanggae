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
/*	
	@RequestMapping("home.do")
	public String home() {

		return "home";
	}*/

	@RequestMapping("loginview.do")
	public String loginpage() {
//member_loginview
		return "member_login";
	}


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
	public ModelAndView login(HttpServletRequest request, HttpServletResponse repuest,MemberVO vo){
		
		if(vo.getMember_id().equals("admingalbage")){
			MemberVO admin=memberService.adminlogin(vo);
			if(admin!=null){		
				HttpSession session = request.getSession(true);			
				session.setAttribute("managerlogin", admin);
				return new ModelAndView("home");
			}			
			return new ModelAndView("member_loginfail");
		}
		
		MemberVO member=memberService.login(vo);
		if(member!=null){ 
			HttpSession session = request.getSession(true);	
			session.setAttribute("mvo", member);
			return new ModelAndView("home");
		}else{			
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
		
		return "home";		
	}
	
	@RequestMapping("updatecancel.do")
	public String updatecancel(){
		
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
		System.out.println("update : " + vo);
		if(session!=null){
			//세션이 있으면
			MemberVO member=memberService.updateMember(vo);
			session.invalidate();
			HttpSession session1 = request.getSession(true);
			session1.setAttribute("mvo", member);
			return new ModelAndView("member_memberUpdateOk");	
		}
		return new ModelAndView("redirect:home.do");
			
	}

	@RequestMapping("withdrawForm.do")
	public String withdrawForm(){
		return "member_withdraw";	
	}

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
	

	@RequestMapping("memberManagerForm.do")
	public ModelAndView memberManagerForm(HttpServletRequest request,HttpServletResponse response){

		String pageNo=request.getParameter("pageNo");
		MemberListVO mvolist=memberService.memberManagerList(pageNo);
		System.out.println(mvolist);
	
		return new ModelAndView("member_memberManagerForm","memberList",mvolist);
	}
	
	@RequestMapping("memberDelete.do")
	public ModelAndView memberDelete(HttpServletRequest request,HttpServletResponse response){
		String member_id=request.getParameter("member_id");
		memberService.memberDelete(member_id);
		String pageNo=request.getParameter("pageNo");
		MemberListVO mvolist = memberService.memberManagerList(pageNo);
		System.out.println(mvolist);

		return new ModelAndView("member_memberManagerDeleteOk");	
	}
	
	
	
	//CCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCC
	/**
	 * 	(right 부분)
	 * 	검색하고자 하는 친구의 ID를 검색하는 메서드
	 * 	검색시 입력어가 포함된 모든 ID가 리스트로 출력된다.
	 * @param member_id
	 * @param sessionId
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("findMemberById.do")
	public ModelAndView findMemberById(String member_id, String sessionId) throws Exception{
		return new ModelAndView("follow_findbyid", "mvoList", memberService.findMemberById(member_id, sessionId));
	}
	
	/**
	 * 	(right 부분)
	 * 	친구 ID 검색 후, 팔로잉을 하는 메서드
	 * 	'+팔로우' 버튼 클릭과 동시에 친구추가가 되고 'v팔로잉' 버튼으로 변경된다.
	 * @param fvo
	 * @throws Exception
	 */
	@RequestMapping("add.do")
	public void addFollow(FollowVO fvo) throws Exception {
		memberService.addFollow(fvo);
	}
	
	/**
	 * 	(right 부분)
	 * 	팔로잉한 친구를 팔로잉 취소하는 메서드
	 * 	'v팔로잉' 버튼 클릭과 동시에 친구삭제가 되고 '+팔로우' 버튼으로 변경된다.
	 * @param vo
	 * @throws Exception
	 */
	@RequestMapping("delete.do")
	public void deleteFollow(FollowVO vo) throws Exception {
		memberService.deleteFollow(vo);
	}
	
	/**
	 * 	(right 부분)
	 * 	팔로잉 버튼 클릭시
	 * 	내가 following 한 친구 리스트를 보여준다.
	 * @param member_id
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("findFollowingId.do")
	public ModelAndView findFollowingId(String member_id) throws Exception {
		List<FollowVO> list = memberService.findFollowingId(member_id);
		return new ModelAndView("follow_followingid", "followingList", list);
	}

	/**
	 * 	(right 부분)
	 * 	팔로워 버튼 클릭시
	 * 	나를 following 한 친구 리스트를 보여준다.
	 * @param member_id
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("findFollowerId.do")
	public ModelAndView findFollowerId(String member_id) throws Exception {
		List<FollowVO> list = memberService.findFollowerId(member_id);
		return new ModelAndView("follow_followerid", "followerList", list);
	}
	
	/**
	 * 	(right 부분)
	 * 	친구 ID 검색시 입력한 검색어가 포함된 ID들을 실시간으로 보여준다.
	 * @param searchId
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("onkeyupId.do")
	public ModelAndView onkeyupId(String searchId) throws Exception{
		//System.out.println(searchId);
		return new ModelAndView("ajaxView", "svoList", memberService.onkeyupId(searchId));
	}

}
