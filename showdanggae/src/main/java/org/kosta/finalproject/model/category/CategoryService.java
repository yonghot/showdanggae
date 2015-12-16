package org.kosta.finalproject.model.category;

import java.util.List;

public interface CategoryService {
	public void addMyCategory(String category, String member_id);
	
	public void deleteProductList(int category_id);
	
	public List<CategoryVO> getMainCategoryList();
	public List<CategoryVO> getMemberCategoryList(String member_id);
	public void addInterest(String interest);
	public int getProductCountNumber(int category_id);
	
	
}
