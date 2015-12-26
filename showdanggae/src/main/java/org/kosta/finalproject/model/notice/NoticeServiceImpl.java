package org.kosta.finalproject.model.notice;

import java.util.ArrayList;
import javax.annotation.Resource;
import org.springframework.stereotype.Service;

@Service
public class NoticeServiceImpl implements NoticeService{
	
	@Resource
	private NoticeDAO noticeDAO;

	/**
	 * 
	 * @Method Name  : noticeList
	 * @작성일   : 2015. 12. 23. 
	 * @작성자   : 유서정
	 * @변경이력  :
	 * @Method 설명 :관리자가 쓴 공지사항들을  list로 보여준다
	 * 							한 페이지당 보여지는 리스트 갯수를 초과할 수 있으므로
	 * 						 	page를 변수로 받아 그에 해당하는 list 결과를 보여준다
	 * @param pageNo
	 * @return
	 */
	@Override
	public ListVO noticeList(String pageNo) {
		PagingBean pagingBean=new PagingBean();
		ArrayList<NoticeVO> noticeList =new ArrayList<NoticeVO>();
		ListVO list=new ListVO(noticeList, pagingBean);
		
		int pn=1;
		if(pageNo!=null){		
			//선택한 pageNo 값이 들어있으면 pageNo를 int형으로 변환해서 해당하는 리스트들을 검색한다
			pn=Integer.parseInt(pageNo);
			noticeList=(ArrayList<NoticeVO>) noticeDAO.noticeList(pn);
			
			int count=noticeDAO.getCount(); 
			pagingBean=new PagingBean(count,pn); 
			list=new ListVO(noticeList,pagingBean);
		}else{			
			//선택한 pageNo값이 없다면 정의해논 pn값으로 pageNo를 대신한다
			int count=noticeDAO.getCount();
			noticeList=(ArrayList<NoticeVO>) noticeDAO.noticeList(pn);
			pagingBean=new PagingBean(count,pn);
			list=new ListVO(noticeList,pagingBean);
		}	
		return list;
	}

/**
 * 
 * @Method Name  : noticeContent
 * @작성일   : 2015. 12. 23. 
 * @작성자   : 유서정
 * @변경이력  :
 * @Method 설명 : 회원들이 공지사항 게시글을 클릭해서 정보를 확인할때마다 
 * 조회수를 증가시켜준다
 * @param no
 * @return
 */
	@Override
	public NoticeVO noticeContent(int no) {
		noticeDAO.plusHit(no);
		return noticeDAO.noticeContent(no);
	}

/**
 * 
 * @Method Name  : noHitnoticeContent
 * @작성일   : 2015. 12. 23. 
 * @작성자   : 유서정
 * @변경이력  :
 * @Method 설명 : 공지사항 게시글의 조회수를 수정하지 않고 Content만 조회한다
 * @param no
 * @return
 */
	@Override
	public NoticeVO noHitnoticeContent(int no) {
		return noticeDAO.noticeContent(no);
	}

/**
 * 
 * @Method Name  : write
 * @작성일   : 2015. 12. 23. 
 * @작성자   : 유서정
 * @변경이력  :
 * @Method 설명 :관리자가 쓴 공지사항 내용들을 저장한다
 * @param vo
 */
	@Override
	public void write(NoticeVO vo) {
		noticeDAO.write(vo);
	}

/**
 * 
 * @Method Name  : noticeUpdate
 * @작성일   : 2015. 12. 23. 
 * @작성자   : 유서정
 * @변경이력  :
 * @Method 설명 : 관리자가 올렸던 공지사항 게시글을 수정한 후 수정한내용으로 새로 저장
 * @param vo
 * @return
 */
	@Override
	public NoticeVO noticeUpdate(NoticeVO vo) {
		noticeDAO.noticeUpdate(vo);
		int no=vo.getNo();
		return noticeDAO.noticeContent(no);
	}

/**
 * 
 * @Method Name  : noticeDelete
 * @작성일   : 2015. 12. 23. 
 * @작성자   : 유서정
 * @변경이력  :
 * @Method 설명 : 관리자가 게시한 공지사항 번호를 배열로 받아서 
 * 						    배열값에 해당되는 공지사항을 삭제한다
 * @param string
 */
	@Override
	public void noticeDelete(String string) {
		noticeDAO.noticeDelete(string);
	}

/**
 * 
 * @Method Name  : deleteContent
 * @작성일   : 2015. 12. 23. 
 * @작성자   : 유서정
 * @변경이력  :
 * @Method 설명 : 관리자가 공지사항 내용을 보며 삭제할 수 있다
 * @param no
 */
	@Override
	public void deleteContent(int no) {
		 noticeDAO.deleteContent(no);
	}

}
