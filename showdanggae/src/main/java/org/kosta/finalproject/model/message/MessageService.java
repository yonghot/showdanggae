package org.kosta.finalproject.model.message;



public interface MessageService {

	void sendMessage(MessageVO vo);

	MessageListVO myMessageBox(String member_id,String pageNo);

	MessageVO MyMessageShowPopUp(MessageVO vo);

	void messageRead(int mno);

}