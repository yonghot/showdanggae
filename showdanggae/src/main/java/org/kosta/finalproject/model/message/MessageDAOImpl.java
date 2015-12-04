package org.kosta.finalproject.model.message;

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
	public List<MessageVO> myMessageBox(String member_Id) {
		// TODO Auto-generated method stub
		return sqlSessionTemplate.selectList("message.myMessageBox", member_Id);
	}

	@Override
	public MessageVO MyMessageShowPopUp(MessageVO vo) {
		// TODO Auto-generated method stub
		return sqlSessionTemplate.selectOne("message.MyMessageShowPopUp", vo);
	}



	@Override
	public void messageRead(int m) {
		sqlSessionTemplate.update("message.messageRead", m);
		
	}

}
