package org.kosta.finalproject.model.product;

import java.util.HashMap;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

@Service
public class ProductServiceImpl implements ProductService {
	
	@Resource
	private ProductDAO productDAO;

	@Override
	public List<ProductVO> MemberProductListAndDeleteCategory(int category_id) {
		return productDAO.MemberProductListAndDeleteCategory(category_id);
	}
	
	@Override
	public List<ProductVO> getMyProductList(String member_id, String currentCategory) {
		HashMap<String, String> map = new HashMap<String, String>();
		map.put("member_id", member_id);
		map.put("currentCategory", currentCategory);
		return productDAO.getMyProductList(map);
	}
	
}
