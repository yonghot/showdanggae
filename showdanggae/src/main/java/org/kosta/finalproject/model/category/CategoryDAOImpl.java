package org.kosta.finalproject.model.category;

import java.util.List;

import javax.annotation.Resource;

import org.kosta.finalproject.model.member.MemberVO;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.stereotype.Repository;
@Repository
public class CategoryDAOImpl implements CategoryDAO{
	@Resource
	private SqlSessionTemplate sqlSessionTemplate;
	
	@Override
	public List<CategoryVO> getMainCategoryList() {
		return sqlSessionTemplate.selectList("category.getMainCategoryList");
	}
	@Override
	public List<CategoryVO> getMemberCategoryList(String member_id) {
		return sqlSessionTemplate.selectList("category.getMemberCategoryList", member_id);
	}
	
	
	@Override
	public void deleteCategory(int category_id) {
		sqlSessionTemplate.delete("category.deleteCategory", category_id);
	}
	@Override
	public void addMyCategory(String category, MemberVO vo) {
		sqlSessionTemplate.insert("category.addMyCategory", category);
	}
	@Override
	public void addInterest(MemberVO vo) {
		sqlSessionTemplate.insert("category.addInterest", vo);
	}
	

}
