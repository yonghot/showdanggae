package org.kosta.finalproject.model.member;
import java.util.ArrayList;
/**
 * list : 회원들의 정보를 담은 MemberVO 의 리스트들
 * mpagingBean :  관리자가 보는 회원관리 view 에서 페이징처리를 위한 class
 * @author 유서정
 *
 */

public class MemberListVO {
	private ArrayList<MemberVO> list;
	private MemberPagingBean mpagingBean;
	

	public MemberListVO(ArrayList<MemberVO> list, MemberPagingBean mpagingBean) {
		super();
		this.list = list;
		this.mpagingBean = mpagingBean;
	}


	public ArrayList<MemberVO> getList() {
		return list;
	}


	public void setList(ArrayList<MemberVO> list) {
		this.list = list;
	}


	public MemberPagingBean getMpagingBean() {
		return mpagingBean;
	}


	public void setMpagingBean(MemberPagingBean mpagingBean) {
		this.mpagingBean = mpagingBean;
	}


	@Override
	public String toString() {
		return "MemberListVO [list=" + list + ", mpagingBean=" + mpagingBean
				+ "]";
	}
	
	
	
	
	
	
}
