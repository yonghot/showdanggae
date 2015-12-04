package org.kosta.finalproject.model.product;

import java.util.List;

public interface ProductService {

	List<ProductVO> getMemberProductListForDeleteCategory(int category_id);
}
