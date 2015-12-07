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
	public List<CategoryVO> getMyCategoryList(String member_id) {
		return categoryDAO.getMyCategoryList(member_id);
	}
	@Override
	public List<CategoryVO> getMainCategoryList() {
		return categoryDAO.getMainCategoryList();
	}
	
	@Override
	public void deleteCategory(int category_id) {
		categoryDAO.deleteCategory(category_id);
	}
	@Override
	public void addMyCategory(String category) {
		categoryDAO.addMyCategory(category);
	}
	@Override
	public void addInterest(MemberVO vo) {
		categoryDAO.addInterest(vo);
	}
	
}

