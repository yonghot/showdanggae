package org.kosta.finalproject.model.product;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

@Service
public class ProductServiceImpl implements ProductService {
	
	@Resource
	private ProductDAO productDAO;

	@Override
	public List<ProductVO> getMyProductList(ProductVO pvo) {
		return productDAO.getMyProductList(pvo);
	}
	

}
