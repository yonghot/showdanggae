package org.kosta.finalproject.model.notice;

import java.util.List;
import javax.annotation.Resource;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class NoticeDAOImpl implements NoticeDAO {
	@Resource
	private SqlSessionTemplate sqlSessionTemplate;

	/**
	 * 
	 * @Method Name  : noticeList
	 * @작성일   : 2015. 12. 23. 
	 * @작성자   : 유서정
	 * @변경이력  :
	 * @Method 설명 : 전달받은 페이지번호로 해당하는 list들을 검색
	 * @param pn
	 * @return
	 */
	@Override
	public List<NoticeVO> noticeList(int pn) {
		return sqlSessionTemplate.selectList("notice.noticeList", pn);
	}

	/**
	 * 
	 * @Method Name  : getCount
	 * @작성일   : 2015. 12. 23. 
	 * @작성자   : 유서정
	 * @변경이력  :
	 * @Method 설명 : 공지사항에 게시되어있는 총 게시물 수를 검색
	 * @return
	 */
	@Override
	public int getCount() {
		return sqlSessionTemplate.selectOne("notice.getCount");
	}

	/**
	 * 
	 * @Method Name  : plusHit
	 * @작성일   : 2015. 12. 23. 
	 * @작성자   : 유서정
	 * @변경이력  :
	 * @Method 설명 : 공지사항 글을 클릭하면 조회수를 +1 수정해준다
	 * @param no
	 */
	@Override
	public void plusHit(int no) {
		sqlSessionTemplate.update("notice.plusHit", no);
	}

	/**
	 * 
	 * @Method Name  : noticeContent
	 * @작성일   : 2015. 12. 23. 
	 * @작성자   : 유서정
	 * @변경이력  :
	 * @Method 설명 : 전달받은 공지사항 게시물번호로 해당하는 내용을 검색
	 * @param no
	 * @return
	 */
	@Override
	public NoticeVO noticeContent(int no) {
		return sqlSessionTemplate.selectOne("notice.noticeContent", no);
	}

	/**
	 * 
	 * @Method Name  : write
	 * @작성일   : 2015. 12. 23. 
	 * @작성자   : 유서정
	 * @변경이력  :
	 * @Method 설명 : 새롭게 작성한 공지사항게시글을 저장시켜준다
	 * @param vo
	 */
	@Override
	public void write(NoticeVO vo) {
		sqlSessionTemplate.insert("notice.write", vo);
		
	}

	/**
	 * 
	 * @Method Name  : noticeUpdate
	 * @작성일   : 2015. 12. 24. 
	 * @작성자   : 유서정
	 * @변경이력  :
	 * @Method 설명 : 기존에 등록되어있던 공지사항 글을 새롭게 수정한 내용을 저장한다
	 * @param vo
	 */
	@Override
	public void noticeUpdate(NoticeVO vo) {
		sqlSessionTemplate.update("notice.update", vo);
	}

	/**
	 * 
	 * @Method Name  : noticeDelete
	 * @작성일   : 2015. 12. 23. 
	 * @작성자   : 유서정
	 * @변경이력  :
	 * @Method 설명 : 배열로 받은 공지사항 게시물 번호에 해당하는 내용을 삭제
	 * @param string
	 */
	@Override
	public void noticeDelete(String string) {
		sqlSessionTemplate.delete("notice.selectDelete", string);
	}

	/**
	 * 
	 * @Method Name  : deleteContent
	 * @작성일   : 2015. 12. 23. 
	 * @작성자   : 유서정
	 * @변경이력  :
	 * @Method 설명 : 공지사항 게시물 번호와 해당하는 정보 삭제
	 * @param no
	 */
	@Override
	public void deleteContent(int no) {
		 sqlSessionTemplate.delete("notice.deleteContent", no);
	}


}
