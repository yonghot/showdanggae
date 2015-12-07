package org.kosta.finalproject.model.category;

import java.util.List;

import javax.annotation.Resource;

import org.kosta.finalproject.model.member.MemberVO;
import org.springframework.stereotype.Service;

@Service
public class CategoryServiceImpl implements CategoryService {
	@Resource
	private CategoryDAO categoryDAO;
	@Override
	public List<CategoryVO> getMemberCategoryList(String member_id) {
		return categoryDAO.getMemberCategoryList(member_id);
	}
	@Override
	public List<CategoryVO> getMainCategoryList() {
		return categoryDAO.getMainCategoryList();
	}
	
	@Override
	public void DeleteCategory(int category_id) {
		categoryDAO.DeleteCategory(category_id);
	}
	@Override
	public void addMyCategory(String category) {
		categoryDAO.addMyCategory(category);
	}
	
}

