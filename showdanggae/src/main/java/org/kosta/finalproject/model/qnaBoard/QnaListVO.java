package org.kosta.finalproject.model.qnaBoard;


import java.util.List;


public class QnaListVO {
	
	private List<QnaVO> list;
	private QPagingBean qpagingBean;
	
	
	public QnaListVO() {
		super();
		// TODO Auto-generated constructor stub
	}


	public QnaListVO(List<QnaVO> list, QPagingBean qpagingBean) {
		super();
		this.list = list;
		this.qpagingBean = qpagingBean;
	}


	public List<QnaVO> getList() {
		return list;
	}


	public void setList(List<QnaVO> list) {
		this.list = list;
	}


	public QPagingBean getQpagingBean() {
		return qpagingBean;
	}


	public void setQpagingBean(QPagingBean qpagingBean) {
		this.qpagingBean = qpagingBean;
	}


	@Override
	public String toString() {
		return "QnaListVO [list=" + list + ", qpagingBean=" + qpagingBean + "]";
	}
	
	
	



}
