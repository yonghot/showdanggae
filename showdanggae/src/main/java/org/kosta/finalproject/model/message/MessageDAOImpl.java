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

/**
 * 
 * @Method Name  : sendMessage
 * @작성일   : 2015. 12. 23. 
 * @작성자   : 유서정
 * @변경이력  :
 * @Method 설명 : 회원과 관리자가 상대방에게 보낸 메세지정보를 저장
 * @param vo
 */
	@Override
	public void sendMessage(MessageVO vo) {
		sqlSessionTemplate.insert("message.sendMessage", vo);
	}

/**
 * 
 * @Method Name  : myMessageBox
 * @작성일   : 2015. 12. 23. 
 * @작성자   : 유서정
 * @변경이력  :
 * @Method 설명 :회원아이디와 페이지번호를 map에 변수로 담아 받은 메세지들을 검색
 * @param map
 * @return
 */
	@Override
	public List<MessageVO> myMessageBox(HashMap<String, String> map) {
		return sqlSessionTemplate.selectList("message.myMessageBox", map);
	}
	/**
	 * 
	 * @Method Name  : MyMessageShowPopUp
	 * @작성일   : 2015. 12. 23. 
	 * @작성자   : 유서정
	 * @변경이력  :
	 * @Method 설명 : 메세지 확인을 위해 클릭시 해당된 메세지 정보를 검색
	 * @param vo
	 * @return
	 */
	@Override
	public MessageVO MyMessageShowPopUp(MessageVO vo) {
		return sqlSessionTemplate.selectOne("message.MyMessageShowPopUp", vo);
	}

	/**
	 * 
	 * @Method Name  : messageRead
	 * @작성일   : 2015. 12. 23. 
	 * @작성자   : 유서정
	 * @변경이력  :
	 * @Method 설명 : 회원이 메세지를 클릭시 read값을 update시켜 읽음표시로 전환시켜주기위해 수정
	 * @param mno
	 */
	@Override
	public void messageRead(int mno) {
		sqlSessionTemplate.update("message.messageRead", mno);
	}

	
	/**
	 * 
	 * @Method Name  : mCount
	 * @작성일   : 2015. 12. 23. 
	 * @작성자   : 유서정
	 * @변경이력  :
	 * @Method 설명 :로그인한 회원이 받은 메세지의 총 개수를 검색
	 * @param member_id
	 * @return
	 */
	@Override
	public int mCount(String member_id) {
		return sqlSessionTemplate.selectOne("message.mCount",member_id);
	}


}
