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
	public List<ProductVO> MemberProductListAndDeleteCategory(int category_id) {
		return  sqlSessionTemplate.selectList("product.MemberProductListAndDeleteCategory", category_id);
	}
	
	@Override
	public List<ProductVO> getMyProductList(Map<String, String> map) {
		return sqlSessionTemplate.selectList("product.getMyProductList", map);
	}

}
