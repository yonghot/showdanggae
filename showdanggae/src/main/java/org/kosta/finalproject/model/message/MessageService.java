package org.kosta.finalproject.model.message;

import java.util.List;

public interface MessageService {

	void sendMessage(MessageVO vo);

	List<MessageVO> myMessageBox(String member_Id);

	MessageVO MyMessageShowPopUp(MessageVO vo);

	void messageRead(String mno);

}