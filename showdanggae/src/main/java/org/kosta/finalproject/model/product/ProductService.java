package org.kosta.finalproject.model.product;

import java.util.List;

public interface ProductService {

	public List<ProductVO> MemberProductListAndDeleteCategory(int category_id);
	public List<ProductVO> getMyProductList(String member_id, String currentCategory);
	public List<ProductVO> getAllBoardList(String sortBy);
	
}
