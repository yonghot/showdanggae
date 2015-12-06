package org.kosta.finalproject.model.category;

import java.util.List;

public interface CategoryDAO {
	public List<CategoryVO> getMemberCategoryList(String member_id);
	public List<CategoryVO> getMainCategoryList();
	public void DeleteCategory(int category_id);
	public void addMyCategory(String category);
}
