package org.kosta.finalproject.model.product;

import java.util.List;
import java.util.Map;

public interface ProductDAO {
	
	public void deleteProduct(int product_id);
	
	public void deleteProductList(int category_id);
	
	List<ProductVO> getMyProductList(Map<String, String> map);

	List<ProductVO> getAllBoardList(String sortBy);
}
