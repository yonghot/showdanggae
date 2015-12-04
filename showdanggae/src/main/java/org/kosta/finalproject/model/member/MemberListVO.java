package org.kosta.finalproject.model.member;

import java.util.ArrayList;


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
