package org.kosta.finalproject.model.message;

import java.util.ArrayList;

public class MessageListVO {
	private ArrayList<MessageVO> list;
	private MsPagingBean mspagingBean;
	
	
	
	public MessageListVO() {
		super();
		// TODO Auto-generated constructor stub
	}



	public MessageListVO(ArrayList<MessageVO> list, MsPagingBean mspagingBean) {
		super();
		this.list = list;
		this.mspagingBean = mspagingBean;
	}



	public ArrayList<MessageVO> getList() {
		return list;
	}



	public void setList(ArrayList<MessageVO> list) {
		this.list = list;
	}



	public MsPagingBean getMspagingBean() {
		return mspagingBean;
	}



	public void setMspagingBean(MsPagingBean mspagingBean) {
		this.mspagingBean = mspagingBean;
	}



	@Override
	public String toString() {
		return "MessageListVO [list=" + list + ", mspagingBean=" + mspagingBean
				+ "]";
	}
	
	
	
	
}