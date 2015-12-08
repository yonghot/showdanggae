package org.kosta.finalproject.model.product;

import java.util.HashMap;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

@Service
public class ProductServiceImpl implements ProductService {
	@Resource
	private ProductDAO productDAO;
	
	
	// 강민석 영역
	

	@Override
	public void deleteProduct(int product_id){
		productDAO.deleteProduct(product_id);
	}
	@Override
	public void deleteProductList(int category_id) {
		productDAO.deleteProductList(category_id);
	}
	
	
	// 김용호 영역
	
	
	@Override
	public List<ProductVO> getMyProductList(String member_id, String currentCategory) {
		HashMap<String, String> map = new HashMap<String, String>();
		map.put("member_id", member_id);
		map.put("currentCategory", currentCategory);
		return productDAO.getMyProductList(map);
	}
	
	@Override
	public List<ProductVO> getAllBoardList(String sortBy) {
		return productDAO.getAllBoardList(sortBy);
	}
	
	@Override
	public List<String> getItemList() {
		return productDAO.getItemList();
	}
	@Override
	public void addProductWithSellerLinkAndEvaluating(ProductVO pvo, SellerLinkVO svo, EvaluatingItemVO evo) {
		productDAO.addProduct(pvo);
		System.out.println(pvo);
		
		svo.setProduct_id(pvo.getProduct_id());
		evo.setProduct_id(pvo.getProduct_id());
		
		productDAO.addSellerLink(svo);
		productDAO.addEvaluatingItem(evo);
	}
	
}
