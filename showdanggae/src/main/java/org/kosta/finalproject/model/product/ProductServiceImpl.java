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
<<<<<<< HEAD
	public List<ProductVO> getMemberProductListForDeleteCategory(int category_id) {
		return productDAO.getMemberProductListForDeleteCategory(category_id);
	}

	//@Override
	//public List<ProductVO> getMemberProductListForDeleteCategory(id) {
		//return productDAO.getMemberProductListForDeleteCategory(id);
=======
	public List<ProductVO> getMyProductList(String member_id, String currentCategory) {
		
		HashMap<String, String> map = new HashMap<String, String>();
		map.put("member_id", member_id);
		map.put("currentCategory", currentCategory);
		
		return productDAO.getMyProductList(map);
>>>>>>> branch 'master' of https://github.com/yonghot/showdanggae.git
	}
	

//}
