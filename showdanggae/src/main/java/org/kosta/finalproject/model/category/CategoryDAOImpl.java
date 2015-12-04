package org.kosta.finalproject.model.category;

import java.util.List;

import javax.annotation.Resource;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.stereotype.Repository;
@Repository
public class CategoryDAOImpl implements CategoryDAO{
	@Resource
	private SqlSessionTemplate sqlSessionTemplate;
	
	@Override
	public List<CategoryVO> getMemberCategoryList(String member_id){
		return sqlSessionTemplate.selectList("category.getMemberCategoryList", member_id);
	}
}
