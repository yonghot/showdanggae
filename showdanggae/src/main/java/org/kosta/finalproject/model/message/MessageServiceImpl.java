package org.kosta.finalproject.model.message;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.annotation.Resource;

import org.kosta.finalproject.model.notice.ListVO;
import org.kosta.finalproject.model.notice.NoticeVO;
import org.kosta.finalproject.model.notice.PagingBean;
import org.springframework.stereotype.Service;

@Service
public class MessageServiceImpl implements MessageService{
	
	@Resource
	MessageDAO messageDAO;


	@Override
	public void sendMessage(MessageVO vo) {
		messageDAO.sendMessage(vo);
	}

	@Override
	public MessageListVO myMessageBox(String member_id,String pageNo) {
		MsPagingBean msPagingBean=new MsPagingBean();
		ArrayList<MessageVO> mlist=new ArrayList<MessageVO>();
		MessageListVO mlvo=new MessageListVO(mlist,msPagingBean);
	
		
		if(pageNo!=null){
			
			HashMap<String, String> map = new HashMap<String, String>();
			map.put("member_id", member_id);
			map.put("pageNo", pageNo);
			mlist=(ArrayList<MessageVO>)messageDAO.myMessageBox(map); 
			
			int pn=Integer.parseInt(pageNo);
			int count=messageDAO.mCount(member_id); 
			msPagingBean=new MsPagingBean(count,pn); 
			mlvo=new MessageListVO(mlist,msPagingBean);
			
		}else{	
			//page 가0이면
			int count=messageDAO.mCount(member_id); 
			HashMap<String, String> map = new HashMap<String, String>();

			map.put("member_id", member_id);
			map.put("pageNo", "1");
			mlist=(ArrayList<MessageVO>)messageDAO.myMessageBox(map); 
			int pn=1;
			msPagingBean=new MsPagingBean(count,pn); 
			mlvo=new MessageListVO(mlist,msPagingBean);
		}
		

		
		return mlvo;
	}


	@Override
	public MessageVO MyMessageShowPopUp(MessageVO vo) {
		// TODO Auto-generated method stub
		return messageDAO.MyMessageShowPopUp(vo);
	}

	@Override
	public void messageRead(int mno) {
	
		messageDAO.messageRead(mno);
		
	}

}
