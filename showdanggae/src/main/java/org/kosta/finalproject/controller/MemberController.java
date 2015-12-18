package org.kosta.finalproject.controller;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.ServletRequest;
import javax.servlet.http.Cookie;
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

	// BBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBB

	@RequestMapping("idCheck.do")
	@ResponseBody
	public Object idCheck(String member_id) throws Exception {
		MemberVO vo = memberService.idCheck(member_id);
		return vo;
	}

	@RequestMapping("register.do")
	public ModelAndView register(MemberVO vo) {

		MemberVO insertVO = memberService.register(vo);
		try {
			String member_name = URLEncoder.encode(insertVO.getMember_name(),
					"UTF-8");
			return new ModelAndView("redirect:registerF5.do?member_name="
					+ member_name);
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		// 실패했을때
		return new ModelAndView("redirect:registerF5.do?member_name=" + "fail");
	}

	@RequestMapping("registerF5.do")
	public ModelAndView registerF5(String member_name) {
		System.out.println(member_name);
		return new ModelAndView("member_registerokview", "member_name",
				member_name);
	}

	@RequestMapping("registerview.do")
	public String registerview() {
		return "member_registerview";
	}

	// 로그인
	@RequestMapping("login.do")
	public ModelAndView login(HttpServletRequest request,
			HttpServletResponse response, MemberVO vo) {
		if (vo.getMember_id().equals("admingalbage")) {
			MemberVO admin = memberService.adminlogin(vo);
			if (admin != null) {
				HttpSession session = request.getSession(true);
				session.setAttribute("mvo", admin);
				return new ModelAndView("home");
			}
			return new ModelAndView("member_loginfail");
		}

		MemberVO member = memberService.login(vo);
		if (member != null) {
			HttpSession session = request.getSession(true);
			session.setAttribute("mvo", member);
			// 세션쿠키 생성
			Cookie cookie = new Cookie(member.getMember_id(), member.getPassword());//추후 수정 해야함
			String autologin = request.getParameter("autologin");
			int cookie_time=24*7; 
			if(autologin!=null){
				response.addCookie(cookie);
				cookie.setMaxAge(60*60*cookie_time);
				System.out.println("test1 - "+cookie);
			}else{
				response.addCookie(cookie);
				cookie.setMaxAge(60);
				System.out.println("test2 - "+cookie);
			}
			return new ModelAndView("home");
		} else {
			return new ModelAndView("member_loginfail");
		}
	}

	@RequestMapping("logout.do")
	public ModelAndView logout(HttpServletRequest request,
			HttpServletResponse repuest) {
		HttpSession session = request.getSession(false);
		if (session != null) {
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

	@RequestMapping("findIdByEmail.do")
	@ResponseBody
	public Object findIdByEmail(MemberVO vo) {
		MemberVO ivo = memberService.findIdByEmail(vo);
		if (ivo != null) {
			String id = ivo.getMember_id();
			StringBuffer sb = new StringBuffer(id);
			sb.replace(2, 4, "**"); // 아이디 출력시 *포함
			return sb;
		} else {
			return ivo;
		}
	}

	@Autowired
	private EmailSender emailSender;
	@Autowired
	private Email email;

	@RequestMapping("findPassById.do")
	public String findPassById(HttpServletRequest request, MemberVO vo)
			throws Exception {
		String mailId = request.getParameter("email_id");
		String domain = request.getParameter("email_domain");
		MemberVO pvo = memberService.findPassById(vo, mailId, domain);
		if (pvo != null) {
			email.setContent("<body> <div id='readFrame'> "
					+ "<div style='width: 100%; background: #fff; text-align: center; font: normal 12px dotum, '돋움''> "
					+ "<table align='center' width='700' border='0' cellpadding='0' cellspacing='0' style='width: 700px; margin: 0 auto; text-align: left;'> "
					+ "<tbody><tr><td style='height: 60px; background: #ebebeb; border-bottom: 2px solid #ed402e;'>"
					+ "<a href='http://localhost:8888/showdanggae/home.do' target='_blank' style='margin: 0 0 0 45px;'> "
					+ "<img src='http://localhost:8888/showdanggae/img/showdanggae_logo_nobg.PNG' width='150' alt='쇼당개'></a></td>"
					+ "</tr><tr><td><table width='700' border='0' cellpadding='0' cellspacing='0' style='background: #f5f5f5;'> "
					+ "<tbody><tr><td style='width: 45px;'></td><td><table width='610' border='0' cellpadding='0' cellspacing='0'> "
					+ "<tbody><tr><td height='75' style='border-bottom: 1px solid #1e2024;'> "
					+ "<img src='http://localhost:8888/showdanggae/img/showdanggae_explanation.png' width='150' alt='쇼당개에서 알려드립니다.'>"
					+ "</td> "
					+ "</tr><tr><td height='84' style='border-top: 1px solid #4e5157;'> "
					+ "<p style='margin: 0; color: #363636;'>"
					+ pvo.getMember_name()
					+ "고객님, 안녕하세요.</p> "
					+ "<p style='margin: 9px 0 0; color: #363636;'> "
					+ "요청하신 비밀번호는 아래와 같습니다.</p> "
					+ "</td></tr><tr><td height='50'style='background: #d2d5d9; color: #fb661e; font: bold 15px Tahoma; text-align: center;'> "
					+ pvo.getPassword()
					+ "</td> </tr><tr><td style='padding: 22px 0 50px; color: #363636;'> "
					+ "<p style='margin: 0;'>항상 쇼당개를 사랑해주셔서 감사드리며, 보다 나은 서비스를 위해 최선을 다하겠습니다.</p> "
					+ "<p style='margin: 11px 0 0;'>감사합니다.</p> "
					+ "</td></tr></tbody></table></td><td style='width: 45px;'></td></tr></tbody></table></td> "
					+ "</tr></tbody></table></div></div></body>");
			email.setReceiver(pvo.getEmail());
			email.setSubject(pvo.getMember_id() + "고객님 요청하신 비밀번호입니다.");
			emailSender.SendEmail(email);

			return "member_findpass";
		} else {
			return "member_findfailpass";
		}
	}

	@RequestMapping("registercancel.do")
	public String registercancel() {
		return "home";
	}

	@RequestMapping("auth_updatecancel.do")
	public String updatecancel() {
		return "home";
	}

	@RequestMapping("auth_updateMember.do")
	public ModelAndView update(HttpServletRequest request,
			HttpServletResponse response, MemberVO vo) {
		HttpSession session = request.getSession(false);
		
		if (session != null) {
			// 세션이 있으면
			MemberVO member = memberService.updateMember(vo);
			session.invalidate();
			HttpSession session1 = request.getSession(true);
			session1.setAttribute("mvo", member);
			return new ModelAndView("member_memberUpdateOk");
		}
		return new ModelAndView("redirect:home.do");

	}

	@RequestMapping("auth_withdrawForm.do")
	public String withdrawForm() {
		return "member_withdraw";
	}

	@RequestMapping("auth_withdraw.do")
	public String withdraw(HttpServletRequest request, MemberVO vo) {
		String reason = request.getParameter("reason");
		memberService.withdraw(vo.getMember_id(), reason);

		HttpSession session = request.getSession(false);
		if (session != null) {
			session.invalidate();
		}

		return "member_withdrawOk";
	}

	@RequestMapping("memberManagerForm.do")
	public ModelAndView memberManagerForm(String pageNo) {

		MemberListVO mvolist = memberService.memberManagerList(pageNo);
		System.out.println(mvolist);

		return new ModelAndView("member_memberManagerForm", "memberList",
				mvolist);
	}

	@RequestMapping("memberDelete.do")
	public ModelAndView memberDelete(HttpServletRequest request,
			HttpServletResponse response) {
		String member_id = request.getParameter("member_id");
		memberService.memberDelete(member_id);
		String pageNo = request.getParameter("pageNo");
		MemberListVO mvolist = memberService.memberManagerList(pageNo);
		System.out.println(mvolist);

		return new ModelAndView("member_memberManagerDeleteOk");
	}

	// CCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCC

	/**
	 * (right 부분) 검색하고자 하는 친구의 ID를 검색하는 메서드 검색시 입력어가 포함된 모든 ID가 리스트로 출력된다.
	 * 
	 * @param member_id
	 * @param sessionId
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("auth_findMemberById.do")
	@ResponseBody
	public List<MemberVO> findMemberById(String member_id, String sessionId)
			throws Exception {
		System.out.println("controller 영역 " + member_id + " " + sessionId);
		List<MemberVO> list = memberService
				.findMemberById(member_id, sessionId);
		return list;
	}

	/**
	 * (right 부분) 친구 ID 검색 후, 팔로잉을 하는 메서드 '+팔로우' 버튼 클릭과 동시에 친구추가가 되고 'v팔로잉' 버튼으로
	 * 변경된다.
	 * 
	 * @param fvo
	 * @throws Exception
	 */
	@RequestMapping("auth_add.do")
	@ResponseBody
	public void addFollow(FollowVO fvo, HttpServletRequest request)
			throws Exception {

		memberService.addFollow(fvo);
	}

	/**
	 * (right 부분) 팔로잉한 친구를 팔로잉 취소하는 메서드 'v팔로잉' 버튼 클릭과 동시에 친구삭제가 되고 '+팔로우' 버튼으로
	 * 변경된다.
	 * 
	 * @param vo
	 * @throws Exception
	 */
	@RequestMapping("auth_delete.do")
	@ResponseBody
	public void deleteFollow(FollowVO vo, HttpServletRequest request)
			throws Exception {
		memberService.deleteFollow(vo);
	}

	/**
	 * (right 부분) 팔로잉 버튼 클릭시 내가 following 한 친구 리스트를 보여준다.
	 * 
	 * @param member_id
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("auth_findFollowingId.do")
	@ResponseBody
	public List<FollowVO> findFollowingId(String member_id) throws Exception {
		// System.out.println(member_id);
		List<FollowVO> list = memberService.findFollowingId(member_id);
		// System.out.println(list);
		// return new ModelAndView("follow_followingid", "followingList", list);
		return list;
	}

	/**
	 * (right 부분) 팔로워 버튼 클릭시 나를 following 한 친구 리스트를 보여준다.
	 * 
	 * @param member_id
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("auth_findFollowerId.do")
	@ResponseBody
	public List<FollowVO> findFollowerId(String member_id) throws Exception {
		List<FollowVO> list = memberService.findFollowerId(member_id);
		return list;
		// return new ModelAndView("follow_followerid", "followerList", list);
	}

	/**
	 * (right 부분) 친구 ID 검색시 입력한 검색어가 포함된 ID들을 실시간으로 보여준다.
	 * 
	 * @param searchId
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("auth_onkeyupId.do")
	@ResponseBody
	public Object onkeyupId(String searchId) throws Exception {
		System.out.println(searchId);
		if (searchId.equals("")) {
			return "";
		}
		List<MemberVO> mid = memberService.onkeyupId(searchId);
		System.out.println(mid);
		return mid;
	}

	// 12-13추가부분
	@RequestMapping("falarm.do")
	@ResponseBody
	public List<FollowVO> fAlarm(String following) {
		// 현재시간부터 - 어제 까지 나를 팔로잉한 사람 목록을 알림으로 가져다 줌
		List<FollowVO> fvo = memberService.fAlarm(following);
		// System.out.println(fvo);
		return fvo;
	}


	// 프로필 수정하는 곳
	@RequestMapping("Profile.do")
	public ModelAndView Profile(String member_id){
		//member_profile로 관심사 리스트 보내줌
		ModelAndView mv=new ModelAndView();
		mv.setViewName("member_profile");
		//내가 선택한걸 제외한 관심사
		List<String> interestList=memberService.profileInterestList(member_id);
		//내가 이미 선택한 관심사
		List<String> myinterestList=memberService.myinterestList(member_id);
		
		mv.addObject("interestList", interestList);
		mv.addObject("myinterestList", myinterestList);
		
		return mv;
	}


	// left 정보
	@RequestMapping("profileInfo.do")
	@ResponseBody
	public HashMap<String, String> profileInfo(String member_id) {
	
		HashMap<String, String> proInfo =new  HashMap<String, String>();
		//내 게시물수
		
		//팔로워 팔로잉 수 + 나의관심사
		proInfo=memberService.proCount(member_id);
		return proInfo;
	}
	
	@RequestMapping("profileInterest.do")
	@ResponseBody
	public List<String> profileInterest(String member_id){
		List<String> interest=memberService.profileInterest(member_id);
	
		return interest;
	}
	
	@RequestMapping("infoUpdate.do")
	public String infoUpdate(MemberVO vo,HttpServletRequest request){
		HttpSession session = request.getSession(false);
		MemberVO mvo=memberService.infoUpdate(vo);
		//세션 끊었다가 다시 연결 시켜줘야함
		if(session!=null){
			session.invalidate();
			HttpSession session1 = request.getSession(true);
			session1.setAttribute("mvo", mvo);
		}
		return "redirect:Profile.do?member_id=" + vo.getMember_id();
	}
	
	@RequestMapping("interestAdd.do")
	public String interestAdd(String member_id,String category){
		memberService.interestAdd(member_id,category);
		return "redirect:Profile.do?member_id=" + member_id;
	}
	
	@RequestMapping("interestDel.do")
	public String interestDel(String member_id,String category){
		memberService.interestDel(member_id, category);
		return "redirect:Profile.do?member_id=" + member_id;

	}
	
	

}
