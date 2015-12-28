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

/**
 * 
 * @Method Name  : idCheck
 * @작성일   : 2015. 12. 22. 
 * @작성자   : 유서정
 * @변경이력  :
 * @Method 설명 : 회원가입창에서 입력받은 member_id 에 대한 중복 여부를 알려준다
 * @param member_id
 * @return
 * @throws Exception
 */
	@RequestMapping("idCheck.do")
	@ResponseBody
	public Object idCheck(String member_id) throws Exception {
		MemberVO vo = memberService.idCheck(member_id);
		return vo;
	}


/**
 * 
 * @Method Name  : register
 * @작성일   : 2015. 12. 22. 
 * @작성자   : 유서정
 * @변경이력  :
 * @Method 설명 : 회원가입창에서 입력받은 회원정보를 저장
 * @param vo
 * @return
 */
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
			return new ModelAndView("redirect:registerF5.do?member_name=" + "fail");
		}
	}

/**
 * 
 * @Method Name  : registerF5
 * @작성일   : 2015. 12. 22. 
 * @작성자   : 유서정
 * @변경이력  :
 * @Method 설명 : 회원가입 후 F5 입력시 회원정보 재등록 방지
 * @param member_name
 * @return
 */
	@RequestMapping("registerF5.do")
	public ModelAndView registerF5(String member_name) {
		return new ModelAndView("member_registerokview", "member_name",
				member_name);
	}

/**
 * 
 * @Method Name  : registerview
 * @작성일   : 2015. 12. 22. 
 * @작성자   : 유서정
 * @변경이력  :
 * @Method 설명 :회원가입 view로 이동시켜준다
 * @return
 */
	@RequestMapping("registerview.do")
	public String registerview() {
		return "member_registerview";
	}
	

	/**
	 * 이건 성엽오빠가~~~~~~~~~~~~~~
	 * @param request
	 * @param response
	 * @param vo
	 * @return
	 */
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

	/**
	 * 
	 * @Method Name  : logout
	 * @작성일   : 2015. 12. 22. 
	 * @작성자   : 유서정
	 * @변경이력  :
	 * @Method 설명 : 로그아웃 요청 시 세션유무를 판단해 세션을 끊어준다.
	 * @param request
	 * @param repuest
	 * @return
	 */
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

/**
 * 
 * @Method Name  : registercancel
 * @작성일   : 2015. 12. 22. 
 * @작성자   : 유서정
 * @변경이력  :
 * @Method 설명 :register view에서 회원가입 취소버튼을 누르면 홈으로 이동시킨다
 * @return
 */
	@RequestMapping("registercancel.do")
	public String registercancel() {
		return "home";
	}

/**
 * 
 * @Method Name  : updatecancel
 * @작성일   : 2015. 12. 22. 
 * @작성자   : 유서정
 * @변경이력  :
 * @Method 설명 :회원정보 수정 시 취소버튼을 누르면 홈으로 이동시킨다
 * @return
 */
	@RequestMapping("auth_updatecancel.do")
	public String updatecancel() {
		return "home";
	}

/**
 * 
 * @Method Name  : update
 * @작성일   : 2015. 12. 22. 
 * @작성자   : 유서정
 * @변경이력  :
 * @Method 설명 :회원이 정보수정을 요청하면 수정된 정보를 다시 DB에 저장한다
 * @param request
 * @param response
 * @param vo
 * @return
 */
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

	/**
	 * 
	 * @Method Name  : withdrawForm
	 * @작성일   : 2015. 12. 22. 
	 * @작성자   : 유서정
	 * @변경이력  :
	 * @Method 설명 :회원탈퇴 form을 제공해주는 view로 이동시킨다
	 * @return
	 */
	@RequestMapping("auth_withdrawForm.do")
	public String withdrawForm() {
		return "member_withdraw";
	}

	/**
	 * 
	 * @Method Name  : withdraw
	 * @작성일   : 2015. 12. 22. 
	 * @작성자   : 유서정
	 * @변경이력  :
	 * @Method 설명 : 회원탈퇴 사유와 아이디를 입력받아 db에 저장된 회원정보를 삭제한 후
	 * 							탈퇴사유는 txt 파일에 저장시킨다
	 * @param request
	 * @param vo
	 * @return
	 */
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

	/**
	 * 
	 * @Method Name  : memberManagerForm
	 * @작성일   : 2015. 12. 22. 
	 * @작성자   : 유서정
	 * @변경이력  :
	 * @Method 설명 : 관리자만 접근할 수 있는 페이지로 가입완료된 회원들의 정보를 list에 담아 보여준다
	 * 							선택한 page number에 해당한 회원정보 20명을 보여준다
	 * @param pageNo
	 * @return
	 */
	@RequestMapping("memberManagerForm.do")
	public ModelAndView memberManagerForm(String pageNo) {
		MemberListVO mvolist = memberService.memberManagerList(pageNo);
		return new ModelAndView("member_memberManagerForm", "memberList",
				mvolist);
	}

	/**
	 * @Method Name  : memberDelete
	 * @작성일   : 2015. 12. 22. 
	 * @작성자   : 유서정
	 * @변경이력  :
	 * @Method 설명 :관리자가 요청한 회원을 삭제해준다
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping("memberDelete.do")
	public ModelAndView memberDelete(HttpServletRequest request,
			HttpServletResponse response) {
		String member_id = request.getParameter("member_id");
		memberService.memberDelete(member_id);
		return new ModelAndView("member_memberManagerDeleteOk");
	}


	/**
	 * 
	 * @Method 이름 : findMemberById
	 * @Method 설명 : right.jsp에서 검색하고자 하는 친구의 ID를 검색하는 메서드.
	 * 				  검색시 입력어가 포함된 모든 ID가 리스트로 출력된다.
	 * @param member_id
	 * @param sessionId
	 * @return
	 * @throws Exception
	 * @작성일 : 2015. 12. 22.
	 * @작성자 : 송슬하,박준서
	 */
	@RequestMapping("auth_findMemberById.do")
	@ResponseBody
	public List<MemberVO> findMemberById(String member_id, String sessionId)
			throws Exception {
		List<MemberVO> list = memberService
				.findMemberById(member_id, sessionId);
		return list;
	}

	/**
	 * 
	 * @Method 이름 : addFollow
	 * @Method 설명 : right.jsp에서 친구 ID 검색 후, 팔로잉을 하는 메서드 '+팔로우' 버튼 클릭과 동시에 친구추가가 되고
	 * 					 'v팔로잉' 버튼으로 변경된다.
	 * @param fvo
	 * @param request
	 * @throws Exception
	 * @작성일 : 2015. 12. 22.
	 * @작성자 : 송슬하,박준서
	 */
	
	@RequestMapping("auth_add.do")
	@ResponseBody
	public void addFollow(FollowVO fvo, HttpServletRequest request)
			throws Exception {
		memberService.addFollow(fvo);
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
	 * @작성자 : 송슬하,박준서
	 */
	
	@RequestMapping("auth_delete.do")
	@ResponseBody
	public void deleteFollow(FollowVO vo, HttpServletRequest request)
			throws Exception {
		memberService.deleteFollow(vo);
	}

	
	/**
	 * @Method 이름 : findFollowingId
	 * @Method 설명 : right.jsp에서 팔로잉 탭을 클릭했을시 내가 팔로잉한 친구 리스트를 보여준다.
	 * @param member_id
	 * @return
	 * @throws Exception
	 * @작성일 : 2015. 12. 22.
	 * @작성자 : 송슬하,박준서
	 */
	
	@RequestMapping("auth_findFollowingId.do")
	@ResponseBody
	public List<FollowVO> findFollowingId(String member_id) throws Exception {
		List<FollowVO> list = memberService.findFollowingId(member_id);
		// return new ModelAndView("follow_followingid", "followingList", list);
		return list;
	}

	/**
	 * @Method 이름 : findFollowerId
	 * @Method 설명 : right.jsp에서 팔로워 탭을 클릭했을 시 나를 팔로우한 친구 리스트를 보여준다.
	 * @param member_id
	 * @return
	 * @throws Exception
	 * @작성일 : 2015. 12. 22.
	 * @작성자 : 송슬하,박준서
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
	 * @throws Exception 이건 없앴는데 어떻게 해야하나...
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

/**
 * @Method Name  : fAlarm
 * @작성일   : 2015. 12. 22. 
 * @작성자   : 유서정
 * @변경이력  :
 * @Method 설명 : 현재시간부터 24시간 전까지 나를 팔로잉한 사람 목록을 알려준다
 * @param following
 * @return
 */
	@RequestMapping("falarm.do")
	@ResponseBody
	public List<FollowVO> fAlarm(String following) {
		List<FollowVO> fvo = memberService.fAlarm(following);
		return fvo;
	}


/**
 * @Method Name  : Profile
 * @작성일   : 2015. 12. 22. 
 * @작성자   : 유서정
 * @변경이력  :
 * @Method 설명 : 회원의 프로필정보(회원의 관심사리스트)를 제공해주고 
 * 선택한 관심사를 저장시켜준다			 
 * @param member_id
 * @return
 */
	@RequestMapping("Profile.do")
	public ModelAndView Profile(String member_id){
		//member_profile로 관심사 리스트 보내줌
		ModelAndView mv=new ModelAndView();
		mv.setViewName("member_profile");
		
		//내가 선택한 관심사를 제외한 관심사
		List<String> interestList=memberService.profileInterestList(member_id);
		//내가 이미 선택한 관심사
		List<String> myinterestList=memberService.myinterestList(member_id);
		
		mv.addObject("interestList", interestList);
		mv.addObject("myinterestList", myinterestList);
		
		return mv;
	}


	/**
	 * 
	 * @Method Name  : profileInfo
	 * @작성일   : 2015. 12. 22. 
	 * @작성자   : 유서정
	 * @변경이력  :
	 * @Method 설명 : template의 왼쪽에 나타나는 나의 프로필 정보
	 * 				  (게시물,팔로우,팔로잉)를 ajax로 가져다준다 
	 * 				 (회원이 게시한 게시물수, 회원을 받아보는 팔로우 수,
	 * 				 회원이 다른회원을 받아보는 팔로잉 수)
	 * @param member_id
	 * @return
	 */
	@RequestMapping("profileInfo.do")
	@ResponseBody
	public HashMap<String, String> profileInfo(String member_id) {	
		HashMap<String, String> proInfo =new  HashMap<String, String>();
		proInfo=memberService.proCount(member_id);
		return proInfo;
	}
	
	/**
	 * 
	 * @Method Name  : profileInterest
	 * @작성일   : 2015. 12. 22. 
	 * @작성자   : 유서정
	 * @변경이력  :
	 * @Method 설명 :template의 왼쪽에 나타나는 나의 프로필 정보(나의 관심사들)
	 * 를 ajax로 가져다준다
	 * @param member_id
	 * @return
	 */
	@RequestMapping("profileInterest.do")
	@ResponseBody
	public List<String> profileInterest(String member_id){
		List<String> interest=memberService.profileInterest(member_id);
		return interest;
	}
	
	/**
	 * 
	 * @Method Name  : infoUpdate
	 * @작성일   : 2015. 12. 22. 
	 * @작성자   : 유서정
	 * @변경이력  :
	 * @Method 설명 : template의 왼쪽에 나타나는 나의 프로필 정보(내 소개)를 수정					
	 * @param vo
	 * @param request
	 * @return
	 */
	@RequestMapping("infoUpdate.do")
	public String infoUpdate(MemberVO vo,HttpServletRequest request){
		HttpSession session = request.getSession(false);
		MemberVO mvo=memberService.infoUpdate(vo);
	
		if(session!=null){
			session.invalidate();
			HttpSession session1 = request.getSession(true);
			session1.setAttribute("mvo", mvo);
		}
		return "redirect:Profile.do?member_id=" + vo.getMember_id();
	}
	
	/**
	 * 
	 * @Method Name  : interestAdd
	 * @작성일   : 2015. 12. 22. 
	 * @작성자   : 유서정
	 * @변경이력  :
	 * @Method 설명 : profile 수정시 관심사를 추가 할 수 있다
	 * @param member_id
	 * @param category
	 * @return
	 */
	@RequestMapping("interestAdd.do")
	public String interestAdd(String member_id,String category){
		memberService.interestAdd(member_id,category);
		return "redirect:Profile.do?member_id=" + member_id;
	}
	
	/**
	 * 
	 * @Method Name  : interestDel
	 * @작성일   : 2015. 12. 22. 
	 * @작성자   : 유서정
	 * @변경이력  :
	 * @Method 설명 :profile 수정시 관심사를 삭제 할 수 있다
	 * @param member_id
	 * @param category
	 * @return
	 */
	@RequestMapping("interestDel.do")
	public String interestDel(String member_id,String category){
		memberService.interestDel(member_id, category);
		return "redirect:Profile.do?member_id=" + member_id;

	}
	
	

}
