package org.kosta.finalproject.model.category;

import java.util.HashMap;
import java.util.List;

import org.kosta.finalproject.model.member.MemberVO;
import org.kosta.finalproject.model.product.EvaluatingItemVO;
import org.kosta.finalproject.model.product.SellerLinkVO;

public interface CategoryDAO {
	public void addMyCategory(HashMap<String, String> map);
	
	public void deleteProductList(int category_id);
	public void deleteCategory(int category_id);
	public List<SellerLinkVO> findSellerLinkByCategoryId(int category_id);
	public void deleteSellerLink(int category_id);
	public List<EvaluatingItemVO> findEvaluatingItemByCategoryId(int category_id);
	public void deleteEvaluatingItem(int category_id);
	
	
	public List<CategoryVO> getMainCategoryList();
	public List<CategoryVO> getMemberCategoryList(String member_id);
	public void addInterest(String interest);
	public int getProductCountNumber(int category_id);

}
