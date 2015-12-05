package org.kosta.finalproject.model.notice;

import java.util.ArrayList;

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
