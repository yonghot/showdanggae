package org.kosta.finalproject.model.category;

import java.util.HashMap;
import java.util.List;

import javax.annotation.Resource;

import org.kosta.finalproject.model.member.MemberVO;
import org.kosta.finalproject.model.product.ProductDAO;
import org.kosta.finalproject.model.product.ProductVO;
import org.springframework.stereotype.Service;

@Service
public class CategoryServiceImpl implements CategoryService {
	@Resource
	private CategoryDAO categoryDAO;
	private ProductDAO productDAO;
	
	@Override
	public List<String> getMemberProduct_idList(int category_id) {
		return categoryDAO.getMemberProduct_idList(category_id);
	}
	@Override
	public void deleteProductList(int product_id, int category_id) {
		if(productDAO.findSellerLinkByProductId(product_id)!=null) {
			productDAO.deleteSellerLink(product_id);
		}
		if(productDAO.findEvaluatingItemByProductId(product_id)!=null) {
			productDAO.deleteEvaluatingItem(product_id);
		}
		productDAO.deleteProduct(category_id);
		
	}

	@Override
	public List<CategoryVO> getMainCategoryList() {
		return categoryDAO.getMainCategoryList();
	}
	
	@Override
	public List<CategoryVO> getMemberCategoryList(String member_id) {
		return categoryDAO.getMemberCategoryList(member_id);
	}
	
	@Override
	public void deleteCategory(int category_id) {
		categoryDAO.deleteCategory(category_id);
	}
	@Override
	public void addMyCategory(String category, String id) {
		HashMap<String, String> map=new HashMap<String, String>();
		map.put("category", category);
		map.put("id", id);
		categoryDAO.addMyCategory(map);
	}
	@Override
	public void addInterest(MemberVO vo) {
		categoryDAO.addInterest(vo);
	}
	@Override
	public int getProductCountNumber(int category_id) {
		return categoryDAO.getProductCountNumber(category_id);
	}
	
}

