package org.kosta.finalproject.model.message;

import java.util.ArrayList;

/**
 * list : 로그인한 회원에게 온 메세지 정보를 담은 MessageVO 의 리스트들
 * mspagingBean : 로그인한 회원의 메세지함에서 보는 회원관리 view 에서 페이징처리를 위한 class
 * @author 유서정
 *
 */
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