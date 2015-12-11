package org.kosta.finalproject.model.message;

import java.util.List;

public interface MessageDAO {

	void sendMessage(MessageVO vo);

	List<MessageVO> myMessageBox(String member_Id);

	MessageVO MyMessageShowPopUp(MessageVO vo);

	void messageRead(int mno);

}