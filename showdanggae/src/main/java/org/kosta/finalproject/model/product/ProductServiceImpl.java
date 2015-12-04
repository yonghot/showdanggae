package org.kosta.finalproject.model.product;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

@Service
public class ProductServiceImpl implements ProductService {
	
	@Resource
	private ProductDAO productDAO;

	@Override
	public List<ProductVO> getMemberProductListForDeleteCategory(int category_id) {
		return productDAO.getMemberProductListForDeleteCategory(category_id);
	}

	//@Override
	//public List<ProductVO> getMemberProductListForDeleteCategory(id) {
		//return productDAO.getMemberProductListForDeleteCategory(id);
	}
	

//}
