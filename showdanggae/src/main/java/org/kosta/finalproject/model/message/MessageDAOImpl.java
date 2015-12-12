package org.kosta.finalproject.model.message;

import java.util.HashMap;
import java.util.List;

import javax.annotation.Resource;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class MessageDAOImpl implements MessageDAO{
	
	@Resource
	private SqlSessionTemplate sqlSessionTemplate;


	@Override
	public void sendMessage(MessageVO vo) {
		sqlSessionTemplate.insert("message.sendMessage", vo);
	}


	@Override
	public List<MessageVO> myMessageBox(HashMap<String, String> map) {
		return sqlSessionTemplate.selectList("message.myMessageBox", map);
	}
	
	@Override
	public MessageVO MyMessageShowPopUp(MessageVO vo) {
		return sqlSessionTemplate.selectOne("message.MyMessageShowPopUp", vo);
	}

	@Override
	public void messageRead(int mno) {
		sqlSessionTemplate.update("message.messageRead", mno);
	}

	@Override
	public int mCount(String member_id) {
		
		return sqlSessionTemplate.selectOne("message.mCount",member_id);
	}

}
