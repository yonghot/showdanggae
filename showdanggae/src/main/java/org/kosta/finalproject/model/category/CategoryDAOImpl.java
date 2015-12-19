package org.kosta.finalproject.model.category;

import java.util.HashMap;
import java.util.List;

import javax.annotation.Resource;

import org.kosta.finalproject.model.member.MemberVO;
import org.kosta.finalproject.model.product.EvaluatingItemVO;
import org.kosta.finalproject.model.product.SellerLinkVO;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.stereotype.Repository;
@Repository
public class CategoryDAOImpl implements CategoryDAO{
	@Resource
	private SqlSessionTemplate sqlSessionTemplate;
	
	@Override
	public void addMyCategory(HashMap<String, String> map) {
		sqlSessionTemplate.insert("category.addMyCategory", map);
	}
	@Override
	public void deleteProductList(int category_id) {
		sqlSessionTemplate.delete("category.deleteProductList", category_id);
	}
	@Override
	public void deleteCategory(int category_id) {
		sqlSessionTemplate.delete("category.deleteCategory", category_id);
	}
	@Override
	public List<SellerLinkVO> findSellerLinkByCategoryId(int category_id) {
		return sqlSessionTemplate.selectList("category.findSellerLinkByCategoryId", category_id);
	}
	@Override
	public void deleteSellerLink(int category_id) {
		sqlSessionTemplate.delete("category.deleteSellerLink", category_id);		
	}
	@Override
	public List<EvaluatingItemVO> findEvaluatingItemByCategoryId(int category_id) {
		return sqlSessionTemplate.selectList("category.findEvaluatingItemByCategoryId", category_id);
	}
	@Override
	public void deleteEvaluatingItem(int  category_id) {
		sqlSessionTemplate.delete("category.deleteEvaluatingItem", category_id);		
	}
	
	
	@Override
	public List<CategoryVO> getMainCategoryList() {
		return sqlSessionTemplate.selectList("category.getMainCategoryList");
	}
	@Override
	public List<CategoryVO> getMemberCategoryList(String member_id) {
		return sqlSessionTemplate.selectList("category.getMemberCategoryList", member_id);
	}
	@Override
	public void addInterest(String interest) {
		sqlSessionTemplate.insert("category.addInterest", interest);
	}
	@Override
	public int getProductCountNumber(int category_id) {
		System.out.println(sqlSessionTemplate.selectOne("category.getProductCountNumber", category_id));
		return sqlSessionTemplate.selectOne("category.getProductCountNumber", category_id);
	}
	
}
