package org.kosta.finalproject.model.product;

import java.util.List;
import java.util.Map;

public interface ProductDAO {

	List<ProductVO> getMemberProductListForDeleteCategory(int category_id);
	
	List<ProductVO> getMyProductList(Map<String, String> map);
}
