package org.kosta.finalproject.model.notice;

import java.util.ArrayList;
/**
 * list : 관리자가 게시한 공지사항 정보를 담은 NoticeVO 의 리스트들
 * pagingBean : 회원, 비회원, 관리자가 공시사항 view 에서 페이징처리를 위한 class
 * @author 유서정
 *
 */
public class ListVO {
	private ArrayList<NoticeVO> list;
	private PagingBean pagingBean;
	
	
	public ListVO(ArrayList<NoticeVO> list, PagingBean pagingBean) {
		super();
		this.list = list;
		this.pagingBean = pagingBean;
	}


	public ArrayList<NoticeVO> getList() {
		return list;
	}


	public void setList(ArrayList<NoticeVO> list) {
		this.list = list;
	}


	public PagingBean getPagingBean() {
		return pagingBean;
	}


	public void setPagingBean(PagingBean pagingBean) {
		this.pagingBean = pagingBean;
	}


	@Override
	public String toString() {
		return "ListVO [list=" + list + ", pagingBean=" + pagingBean + "]";
	}



}
