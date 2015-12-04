package org.kosta.finalproject.model.product;

import java.util.List;

import javax.annotation.Resource;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class ProductDAOImpl implements ProductDAO {
	
	@Resource
	private SqlSessionTemplate sqlSessionTemplate;

	@Override
	public List<ProductVO> getMemberProductListForDeleteCategory(int category_id) {
		return  sqlSessionTemplate.selectList("product.getMemberProductListForDeleteCategory", category_id);
	}

}
