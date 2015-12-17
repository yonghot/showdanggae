package org.kosta.finalproject.model.message;

import java.util.HashMap;
import java.util.List;




public interface MessageDAO {

	void sendMessage(MessageVO vo);

	List<MessageVO> myMessageBox(HashMap<String,String> map);

	MessageVO MyMessageShowPopUp(MessageVO vo);

	void messageRead(int mno);

	int mCount(String member_id);


}