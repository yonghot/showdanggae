package org.kosta.finalproject.model.category;

import java.util.HashMap;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

@Service
public class CategoryServiceImpl implements CategoryService {
	@Resource
	private CategoryDAO categoryDAO;
	
	@Override
	public void addMyCategory(String category, String member_id) {
		HashMap<String, String> map=new HashMap<String, String>();
		map.put("category", category);
		map.put("member_id", member_id);
		categoryDAO.addMyCategory(map);
	}
	@Override
	public void deleteProductList(int category_id) {
		if(categoryDAO.findSellerLinkByCategoryId(category_id)!=null) {
			categoryDAO.deleteSellerLink(category_id);
		}
		if(categoryDAO.findEvaluatingItemByCategoryId(category_id)!=null) {
			categoryDAO.deleteEvaluatingItem(category_id);
		}
		categoryDAO.deleteProductList(category_id);
		categoryDAO.deleteCategory(category_id);
	}

	@Override
	public List<CategoryVO> getMainCategoryList() {
		return categoryDAO.getMainCategoryList();
	}
	
	@Override
	public List<CategoryVO> getMemberCategoryList(String member_id) {
		return categoryDAO.getMemberCategoryList(member_id);
	}
	
	@Override
	public void addInterest(String interest) {
		categoryDAO.addInterest(interest);
	}
	@Override
	public CategoryVO getProductCountNumber(int category_id) {
		return categoryDAO.getProductCountNumber(category_id);
	}
	
}

