package org.kosta.finalproject.model.product;

import java.util.HashMap;
import java.util.List;

public interface ProductService {
	public List<ProductVO> getMyProductList(String member_id, String currentCategory);
	public void deleteProductList(int category_id);
	public List<ProductVO> getAllBoardList(String sortBy);
	public List<String> getItemList();
	public void addProductWithSellerLinkAndEvaluating(ProductVO pvo, SellerLinkVO slvo, EvaluatingItemVO evo);
	public void hit(String product_id);
	public HashMap<String, Object> showProductContent(int product_id);
	public void updateProduct(int product_id, ProductVO pvo, SellerLinkVO slvo,EvaluatingItemVO evo);
	public void deleteProduct(int product_id);
	public int getCategoryIdByProductId(int product_id);
}
