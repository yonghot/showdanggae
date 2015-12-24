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

/**
 * 
 * @Method Name  : sendMessage
 * @작성일   : 2015. 12. 23. 
 * @작성자   : 유서정
 * @변경이력  :
 * @Method 설명 : 관리자가 회원에게 메세지를 보내줌
 * @param vo
 */
	@Override
	public void sendMessage(MessageVO vo) {
		messageDAO.sendMessage(vo);
	}

	/**
	 * 
	 * @Method Name  : myMessageBox
	 * @작성일   : 2015. 12. 23. 
	 * @작성자   : 유서정
	 * @변경이력  :
	 * @Method 설명 : 회원이 받은 메세지들을 리스트로 뽑아 보여준다
	 * 							메세지리스트 양이 한 페이지에 보여줄 수 있는 양보다 많을 수 있으므로
	 * 							pageNo를 받아 그 페이지에 해당하는 메세지 리스트를 보여준다
	 * @param member_id
	 * @param pageNo
	 * @return
	 */
	@Override
	public MessageListVO myMessageBox(String member_id,String pageNo) {
		MsPagingBean msPagingBean=new MsPagingBean();
		ArrayList<MessageVO> mlist=new ArrayList<MessageVO>();
		MessageListVO mlvo=new MessageListVO(mlist,msPagingBean);
		if(pageNo!=null){		
			//선택한 pageNo 값이 들어있으면 pageNo를 int형으로 변환해서 해당하는 리스트들을 검색한다
			//어떤 회원이 몇페이지 메세지리스트들을 받을 것인지에 대한 setting
			HashMap<String, String> map = new HashMap<String, String>();
			map.put("member_id", member_id);
			map.put("pageNo", pageNo);
			mlist=(ArrayList<MessageVO>)messageDAO.myMessageBox(map); 	
			int pn=Integer.parseInt(pageNo);
			int count=messageDAO.mCount(member_id); 
			msPagingBean=new MsPagingBean(count,pn); 
			mlvo=new MessageListVO(mlist,msPagingBean);
			
		}else{			
			//어떤 회원이 첫번째 메세지리스트들을 받을 것인지에 대한 setting
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


	/**
	 * 
	 * @Method Name  : MyMessageShowPopUp
	 * @작성일   : 2015. 12. 23. 
	 * @작성자   : 유서정
	 * @변경이력  :
	 * @Method 설명 : messageRead 는 메세지를 읽었는지 안읽었는지 확인해준다
	 * 							MyMessageShowPopUp 메세지를 클릭했을때 클릭한 내용을 보여준다
	 * @param vo
	 * @return
	 */
	@Override
	public MessageVO MyMessageShowPopUp(MessageVO vo) {
		messageDAO.messageRead(vo.getMno());
		return messageDAO.MyMessageShowPopUp(vo);
	}


}
