package org.kosta.finalproject.model.product;

import java.util.List;

public interface ProductDAO {

	List<ProductVO> getMemberProductListForDeleteCategory(int category_id);
	
}
