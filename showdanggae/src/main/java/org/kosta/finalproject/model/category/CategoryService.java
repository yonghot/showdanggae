package org.kosta.finalproject.model.category;

import java.util.List;

import org.kosta.finalproject.model.member.MemberVO;

public interface CategoryService {
	public List<CategoryVO> getMainCategoryList();
	public List<CategoryVO> getMemberCategoryList(String member_id);
	public void deleteCategory(int category_id);
	public void addMyCategory(String category, MemberVO vo);
	public void addInterest(MemberVO vo);
	
}
