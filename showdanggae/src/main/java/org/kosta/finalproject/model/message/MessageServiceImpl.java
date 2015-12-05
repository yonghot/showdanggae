package org.kosta.finalproject.model.message;

import java.util.List;
import javax.annotation.Resource;
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
	public List<MessageVO> myMessageBox(String member_Id) {
		
		return messageDAO.myMessageBox(member_Id);
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
