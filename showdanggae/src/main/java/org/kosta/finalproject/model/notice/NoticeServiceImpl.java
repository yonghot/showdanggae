package org.kosta.finalproject.model.notice;

import java.util.ArrayList;
import javax.annotation.Resource;
import org.springframework.stereotype.Service;

@Service
public class NoticeServiceImpl implements NoticeService{
	
	@Resource
	private NoticeDAO noticeDAO;

	@Override
	public ListVO noticeList(String pageNo) {
		PagingBean pagingBean=new PagingBean();
		ArrayList<NoticeVO> noticeList =new ArrayList<NoticeVO>();
		ListVO list=new ListVO(noticeList, pagingBean);
		
		int pn=1;
		if(pageNo!=null){		
			pn=Integer.parseInt(pageNo);
			noticeList=(ArrayList<NoticeVO>) noticeDAO.noticeList(pn); //GETPOSTINGLIST�� ���������ȿ��͸�
			
			int count=noticeDAO.getCount(); //�� �Խù� ����
			pagingBean=new PagingBean(count,pn); //total nowpage
			list=new ListVO(noticeList,pagingBean);
		}else{	
			
			int count=noticeDAO.getCount();
			noticeList=(ArrayList<NoticeVO>) noticeDAO.noticeList(pn);
			pagingBean=new PagingBean(count,pn);//total nowpage
			list=new ListVO(noticeList,pagingBean);
		}
		
		
		return list;
	}


	@Override
	public NoticeVO noticeContent(int no) {
		noticeDAO.plusHit(no);
		return noticeDAO.noticeContent(no);
	}


	@Override
	public NoticeVO noHitnoticeContent(int no) {
	
		return noticeDAO.noticeContent(no);
	}


	@Override
	public void write(NoticeVO vo) {
		noticeDAO.write(vo);
	}


	@Override
	public NoticeVO noticeUpdate(NoticeVO vo) {
		noticeDAO.noticeUpdate(vo);
		int no=vo.getNo();
		return noticeDAO.noticeContent(no);
	}


	@Override
	public void noticeDelete(String string) {
	
		noticeDAO.noticeDelete(string);
	}

}
