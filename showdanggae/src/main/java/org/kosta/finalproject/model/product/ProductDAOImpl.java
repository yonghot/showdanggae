package org.kosta.finalproject.model.product;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class ProductDAOImpl implements ProductDAO {
	@Resource
	private SqlSessionTemplate sqlSessionTemplate;
	
	
	// 강민석 영역
	
	
	@Override
	public void deleteProduct(int product_id) {
		sqlSessionTemplate.delete("product.deleteProduct", product_id);
	}
	@Override
	public void deleteProductList(int category_id) {
		sqlSessionTemplate.delete("product.deleteProductList", category_id);
	}
	
	
	// 김용호 영역
	
	@Override
	public List<ProductVO> getMyProductList(Map<String, String> map) {
		return sqlSessionTemplate.selectList("product.getMyProductList", map);
	}
	@Override
	public List<ProductVO> getAllBoardList(String sortBy) {
		return sqlSessionTemplate.selectList("product.getAllBoardList", sortBy);
	}
	@Override
	public List<String> getItemList() {
		return sqlSessionTemplate.selectList("product.getItemList");
	}
	
	@Override
	public void addProduct(ProductVO pvo) {
		sqlSessionTemplate.insert("product.registProduct", pvo);
	}
	@Override
	public void addSellerLink(SellerLinkVO slvo) {
		sqlSessionTemplate.insert("product.addSellerLink", slvo);
	}
	@Override
	public void addEvaluatingItem(EvaluatingItemVO evo) {
		sqlSessionTemplate.insert("product.addEvaluatingItem", evo);
	}

}
