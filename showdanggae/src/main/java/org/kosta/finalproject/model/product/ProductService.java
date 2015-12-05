package org.kosta.finalproject.model.product;

import java.util.List;

public interface ProductService {
	public List<ProductVO> getMyProductList(String member_id, String currentCategory);
	public void DeleteProduct(int product_id);
	public void DeleteProductList(int category_id);
	
}
