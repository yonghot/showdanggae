package org.kosta.finalproject.model.category;

import java.util.HashMap;
import java.util.List;

import javax.annotation.Resource;

import org.kosta.finalproject.model.member.MemberVO;
import org.kosta.finalproject.model.product.ProductVO;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.stereotype.Repository;
@Repository
public class CategoryDAOImpl implements CategoryDAO{
	@Resource
	private SqlSessionTemplate sqlSessionTemplate;
	
	@Override
	public List<String> getMemberProduct_idList(int category_id) {
		return sqlSessionTemplate.selectList("category.getMemberProduct_idList", category_id);
	}
	@Override
	public void deleteProductList(int product_id) {
		sqlSessionTemplate.delete("product.deleteProductList", product_id);
	}
	@Override
	public void deleteSellerLinkList(int product_id) {
		sqlSessionTemplate.delete("product.deleteSellerLinkList", product_id);
	}
	@Override
	public void deleteEvaluatingItemList(int product_id) {
		sqlSessionTemplate.delete("product.deleteEvaluatingItemList", product_id);
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
	public void deleteCategory(int category_id) {
		sqlSessionTemplate.delete("category.deleteCategory", category_id);
	}
	@Override
	public void addMyCategory(HashMap<String, String> map) {
		sqlSessionTemplate.insert("category.addMyCategory", map);
	}
	@Override
	public void addInterest(MemberVO vo) {
		sqlSessionTemplate.insert("category.addInterest", vo);
	}
	@Override
	public int getProductCountNumber(int category_id) {
		return sqlSessionTemplate.selectOne("category.getProductCountNumber", category_id);
	}
	
}
