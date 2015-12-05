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
	
	@Override
	public void DeleteProduct(int product_id) {
		sqlSessionTemplate.delete("product.DeleteProduct", product_id);
	}
	@Override
	public void DeleteProductList(int category_id) {
		sqlSessionTemplate.delete("product.DeleteProductList", category_id);
	}
	@Override
	public List<ProductVO> getMyProductList(Map<String, String> map) {
		return sqlSessionTemplate.selectList("product.getMyProductList", map);
	}
	
}
