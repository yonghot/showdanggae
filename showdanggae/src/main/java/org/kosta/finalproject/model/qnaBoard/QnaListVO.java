package org.kosta.finalproject.model.qnaBoard;
import java.util.List;
/**
 * list : 회원과 관리자가 게시한 질문과 답변 정보를 담은 QnaVO 의 리스트
 * pagingBean :  QnA view 에서 페이징처리를 위한 class
 * @author 유서정
 *
 */

public class QnaListVO {
	private List<QnaVO> list;
	private QPagingBean qpagingBean;
	
	
	public QnaListVO() {
		super();
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
