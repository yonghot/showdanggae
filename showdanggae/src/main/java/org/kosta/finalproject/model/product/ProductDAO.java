package org.kosta.finalproject.model.product;

import java.util.List;
import java.util.Map;

public interface ProductDAO {
	public void DeleteProduct(int product_id);
	public void DeleteProductList(int category_id);
	List<ProductVO> getMyProductList(Map<String, String> map);
}
