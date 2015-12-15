package org.kosta.finalproject.model.product;

import java.util.HashMap;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

@Service
public class ProductServiceImpl implements ProductService {
	@Resource
	private ProductDAO productDAO;
	
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
		productDAO.addProduct(pvo); //insert는 부모테이블 것을 먼저 해준다
		
		if(slvo.getLink()!=null) { //공란이 DB에 들어가면 null로 인식되서 not null인 변수에 넣을 수가 없다
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
	public HashMap<String, Object> showProductContent(int product_id) {
		
		HashMap<String, Object> productMap = new HashMap<String, Object>(); 
		
		productMap.put("pvo", productDAO.getProductByProduct_id(product_id));
		
		List<SellerLinkVO> sellerLinkList = productDAO.getSellerLinkByProduct_id(product_id); 
		if(sellerLinkList.size()!=0) {
			productMap.put("slvoList", sellerLinkList);
		}
		
		List<EvaluatingItemVO> evaluatingItemList = productDAO.getEvaluatingItemByProduct_id(product_id);
		if(evaluatingItemList.size()!=0) {
			productMap.put("evoList", evaluatingItemList);
		}
		
		return productMap;
	}
	@Override
	public void updateProduct(int product_id, ProductVO pvo, SellerLinkVO slvo, EvaluatingItemVO evo) {
		productDAO.updateProduct(pvo);
		
		if(!slvo.getLink().equals("")) {
			slvo.setProduct_id(product_id);
			
			if(productDAO.getSellerLinkByProduct_id(product_id)!=null) {
				productDAO.updateSellerLink(slvo);
			} else {
				productDAO.addSellerLink(slvo);
			}
		} else {
			productDAO.deleteSellerLink(product_id);
		}
		
		if(evo.getItem()!=null) {
			evo.setProduct_id(product_id);
			productDAO.updateEvaluatingItem(evo);
			
			if(productDAO.getEvaluatingItemByProduct_id(product_id)!=null) {
				productDAO.updateEvaluatingItem(evo);
			} else {
				productDAO.addEvaluatingItem(evo);
			}
		} else {
			productDAO.deleteEvaluatingItem(product_id);
		}
	}
	@Override
	public void deleteProduct(int product_id) {
		
		if(productDAO.findSellerLinkByProductId(product_id)!=null) { //자식 테이블의 fk를 먼저 지워줘야 부모테이블의 데이터를 지울수 있다.
			productDAO.deleteSellerLink(product_id);
		}
		if(productDAO.findEvaluatingItemByProductId(product_id)!=null) {
			productDAO.deleteEvaluatingItem(product_id);
		}
		
		productDAO.deleteProduct(product_id);
	}

	@Override
	public int getCategoryIdByProductId(int product_id) {
		return productDAO.getCategoryIdByProductId(product_id);
	}
}
