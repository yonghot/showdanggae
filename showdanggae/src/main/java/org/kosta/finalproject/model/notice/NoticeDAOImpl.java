package org.kosta.finalproject.model.notice;

import java.util.List;

import javax.annotation.Resource;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class NoticeDAOImpl implements NoticeDAO {
	@Resource
	private SqlSessionTemplate sqlSessionTemplate;

	@Override
	public List<NoticeVO> noticeList(int pn) {
		return sqlSessionTemplate.selectList("notice.noticeList", pn);
	}

	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return sqlSessionTemplate.selectOne("notice.getCount");
	}

	@Override
	public void plusHit(int no) {
		sqlSessionTemplate.update("notice.plusHit", no);
	}

	@Override
	public NoticeVO noticeContent(int no) {
		
		return sqlSessionTemplate.selectOne("notice.noticeContent", no);
	}

	@Override
	public void write(NoticeVO vo) {
		sqlSessionTemplate.insert("notice.write", vo);
		
	}

	@Override
	public void noticeUpdate(NoticeVO vo) {
		sqlSessionTemplate.update("notice.update", vo);
	}

	@Override
	public void noticeDelete(String string) {
		sqlSessionTemplate.delete("notice.selectDelete", string);
	}


}
