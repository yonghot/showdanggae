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
<<<<<<< HEAD
	public List<ProductVO> getMemberProductListForDeleteCategory(int category_id) {
		return  sqlSessionTemplate.selectList("product.getMemberProductListForDeleteCategory", category_id);
=======
	public List<ProductVO> getMyProductList(Map<String, String> map) {
		return sqlSessionTemplate.selectList("product.getMyProductList", map);
>>>>>>> branch 'master' of https://github.com/yonghot/showdanggae.git
	}

}
