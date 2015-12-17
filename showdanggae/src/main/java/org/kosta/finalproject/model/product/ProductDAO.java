package org.kosta.finalproject.model.product;

import java.util.List;
import java.util.Map;

public interface ProductDAO {
	
	List<ProductVO> getMyProductList(Map<String, String> map);

	List<ProductVO> getAllBoardList(String sortBy);

	public List<String> getItemList();

	public void addProduct(ProductVO pvo);

	public void addSellerLink(SellerLinkVO slvo);

	public void addEvaluatingItem(EvaluatingItemVO evo);

	public void hit(String product_id);

	public ProductVO getProductByProduct_id(int product_id);
	public List<SellerLinkVO> getSellerLinkByProduct_id(int product_id);
	public List<EvaluatingItemVO> getEvaluatingItemByProduct_id(int product_id);

	public void updateProduct(ProductVO pvo);
	public void updateSellerLink(SellerLinkVO slvo);
	public void updateEvaluatingItem(EvaluatingItemVO evo);

	public void deleteProduct(int product_id);
	public void deleteSellerLink(int product_id);
	public void deleteEvaluatingItem(int product_id);

	public List<SellerLinkVO> findSellerLinkByProductId(int product_id);
	public List<EvaluatingItemVO> findEvaluatingItemByProductId(int product_id);

	public int getCategoryIdByProductId(int product_id);
<<<<<<< HEAD

	public int getLowestPriceByProductId(int product_id);
=======
	
	
>>>>>>> branch 'master' of https://github.com/yonghot/showdanggae.git
}
