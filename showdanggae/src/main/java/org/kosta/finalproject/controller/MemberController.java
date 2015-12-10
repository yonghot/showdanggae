package org.kosta.finalproject.controller;

import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.kosta.finalproject.model.email.Email;
import org.kosta.finalproject.model.email.EmailSender;
import org.kosta.finalproject.model.member.FollowVO;
import org.kosta.finalproject.model.member.MemberListVO;
import org.kosta.finalproject.model.member.MemberService;
import org.kosta.finalproject.model.member.MemberVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
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
	@RequestMapping("auth_{viewId}.do")
	public String authShowTilesView(@PathVariable String viewId) {
		System.out.println(viewId);
		return viewId;
	}
	
	
	//BBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBB


	@RequestMapping("idCheck.do")
	@ResponseBody
	public Object idCheck(String member_id)throws Exception{
		System.out.println(member_id);
		MemberVO vo = memberService.idCheck(member_id);		

		return vo;

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
		return "member_registerview";
	}
	
	
	@RequestMapping("login.do")
	public ModelAndView login(HttpServletRequest request, MemberVO vo){
		
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
	
	@RequestMapping("findidview.do")
	public String findview() {
		return "member_findidview";
	}

	@RequestMapping("findpassview.do")
	public String findpassview() {
		return "member_findpassview";
	}

	@RequestMapping("findIdByBirth.do")
	public ModelAndView findIdByBirth(HttpServletRequest request, MemberVO vo) {
		String mailId = request.getParameter("email_id");
		String domain = request.getParameter("email_domain");
		MemberVO ivo=memberService.findIdByBirth(vo,mailId,domain);
		return new ModelAndView("member_findid","findId",ivo.getMember_id());
	}

	@Autowired
	private EmailSender emailSender;
	@Autowired
	private Email email;
	
	@RequestMapping("findPassById.do")
	public String findPassById(HttpServletRequest request, MemberVO vo) throws Exception {
		String mailId = request.getParameter("email_id");
		String domain = request.getParameter("email_domain");
		MemberVO pvo = memberService.findPassById(vo, mailId, domain);
		if(pvo!=null){
			email.setContent("비밀번호는 "+pvo.getPassword()+"입니다.");
			email.setReceiver(pvo.getEmail());
			email.setSubject(pvo.getMember_id()+"님의 비밀번호 찾기 발송 이메일입니다.");
			emailSender.SendEmail(email);
			return "member_findpass";	
		}else{
			return "member_findfailpass";
		}
	}
	
	@RequestMapping("registercancel.do")
	public String registercancel(){
		return "home";		
	}
	
	@RequestMapping("updatecancel.do")
	public String updatecancel(){
		return "home";		
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
	@RequestMapping("auth_findMemberById.do")
	public ModelAndView findMemberById(String member_id, String sessionId) throws Exception{
		System.out.println(member_id+sessionId);
		return new ModelAndView("follow_findbyid", "mvoList", memberService.findMemberById(member_id, sessionId));
	}
	
	/**
	 * 	(right 부분)
	 * 	친구 ID 검색 후, 팔로잉을 하는 메서드
	 * 	'+팔로우' 버튼 클릭과 동시에 친구추가가 되고 'v팔로잉' 버튼으로 변경된다.
	 * @param fvo
	 * @throws Exception
	 */
	@RequestMapping("auth_add.do")
	@ResponseBody
	public void addFollow(FollowVO fvo,HttpServletRequest request) throws Exception {
		memberService.addFollow(fvo);
	}
	
	/**
	 * 	(right 부분)
	 * 	팔로잉한 친구를 팔로잉 취소하는 메서드
	 * 	'v팔로잉' 버튼 클릭과 동시에 친구삭제가 되고 '+팔로우' 버튼으로 변경된다.
	 * @param vo
	 * @throws Exception
	 */
	@RequestMapping("auth_delete.do")
	@ResponseBody
	public void deleteFollow(FollowVO vo, HttpServletRequest request) throws Exception {
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
	@RequestMapping("auth_findFollowingId.do")
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
	@RequestMapping("auth_findFollowerId.do")
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
	@RequestMapping("auth_onkeyupId.do")
	@ResponseBody
	public Object onkeyupId(String searchId) throws Exception{
		if(searchId.equals("")) {
			return "";
		}
		return memberService.onkeyupId(searchId);
	}
}
