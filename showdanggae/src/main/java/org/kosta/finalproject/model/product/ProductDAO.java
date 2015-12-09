package org.kosta.finalproject.model.product;

import java.util.List;
import java.util.Map;

public interface ProductDAO {
	
	public void deleteProduct(int product_id);
	
	public void deleteProductList(int category_id);
	
	List<ProductVO> getMyProductList(Map<String, String> map);

	List<ProductVO> getAllBoardList(String sortBy);

	public List<String> getItemList();

	public void addProduct(ProductVO pvo);

	public void addSellerLink(SellerLinkVO slvo);

	public void addEvaluatingItem(EvaluatingItemVO evo);

	public void hit(String product_id);

	public ProductVO getProductByProduct_id(String product_id);
	public List<SellerLinkVO> getSellerLinkByProduct_id(String product_id);
	public List<EvaluatingItemVO> getEvaluatingItemByProduct_id(String product_id);
}
