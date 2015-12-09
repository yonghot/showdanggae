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
	public void addProductWithSellerLinkAndEvaluating(ProductVO pvo, SellerLinkVO slvo, EvaluatingItemVO evo) {
		productDAO.addProduct(pvo);
		
		if(!slvo.getLink().equals("")) { //공란이 DB에 들어가면 null로 인식되서 not null인 변수에 넣을 수가 없다
			slvo.setProduct_id(pvo.getProduct_id());
			productDAO.addSellerLink(slvo);
		}
		
		if(evo.getItem()!=null) {
			evo.setProduct_id(pvo.getProduct_id());
			productDAO.addEvaluatingItem(evo);
		}
	}
	
	@Override
	public void hit(String product_id) {
		productDAO.hit(product_id);
	}
	@Override
	public HashMap<String, Object> showProductContent(String product_id) {
		
		HashMap<String, Object> productMap = new HashMap<String, Object>(); 
		
		productMap.put("pvo", productDAO.getProductByProduct_id(product_id));
		
		if(productDAO.getSellerLinkByProduct_id(product_id).size()!=0) {
			productMap.put("slvo", productDAO.getSellerLinkByProduct_id(product_id));
		}
		
		if(productDAO.getEvaluatingItemByProduct_id(product_id).size()!=0) {
			productMap.put("evo", productDAO.getEvaluatingItemByProduct_id(product_id));
		}
		
		return productMap;
	}
	
}
