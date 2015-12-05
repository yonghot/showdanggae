package org.kosta.finalproject.model.category;

import java.util.List;

import org.kosta.finalproject.model.member.MemberVO;

public interface CategoryDAO {
	public List<CategoryVO> getMemberCategoryList(String member_id);
}
