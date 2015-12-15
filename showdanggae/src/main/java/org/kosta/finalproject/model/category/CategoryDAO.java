package org.kosta.finalproject.model.category;

import java.util.HashMap;
import java.util.List;

import org.kosta.finalproject.model.member.MemberVO;
import org.kosta.finalproject.model.product.ProductVO;

public interface CategoryDAO {
	public List<String> getMemberProduct_idList(int category_id);
	public void deleteProductList(int product_id);
	public void deleteSellerLinkList(int product_id);
	public void deleteEvaluatingItemList(int product_id);
	public void deleteCategory(int category_id);
	
	public List<CategoryVO> getMainCategoryList();
	public List<CategoryVO> getMemberCategoryList(String member_id);
	public void addInterest(MemberVO vo);
	public void addMyCategory(HashMap<String, String> map);
	public int getProductCountNumber(int category_id);

}
