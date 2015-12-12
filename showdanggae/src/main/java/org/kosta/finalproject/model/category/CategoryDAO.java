package org.kosta.finalproject.model.category;

import java.util.HashMap;
import java.util.List;

import org.kosta.finalproject.model.member.MemberVO;

public interface CategoryDAO {
	public List<CategoryVO> getMainCategoryList();
	public List<CategoryVO> getMemberCategoryList(String member_id);
	public void deleteCategory(int category_id);
	public void addInterest(MemberVO vo);
	public void addMyCategory(HashMap<String, String> map);

}
