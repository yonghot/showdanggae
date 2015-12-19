package org.kosta.finalproject.model.product;

import java.sql.SQLException;
import java.util.ArrayList;
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
		
		List<ProductVO> pvoList = productDAO.getMyProductList(map);
		for(int i=0;i<pvoList.size();i++){
			pvoList.get(i).setLowestPrice(productDAO.getLowestPriceByProductId(pvoList.get(i).getProduct_id()));
		}
		
		return pvoList;
	}
	
	@Override
	public List<ProductVO> getAllProductList(String sortBy) {
		
		List<ProductVO> pvoList = productDAO.getAllProductList(sortBy);
		for(int i=0;i<pvoList.size();i++){
			pvoList.get(i).setLowestPrice(productDAO.getLowestPriceByProductId(pvoList.get(i).getProduct_id()));
		}
		
		return pvoList;
	}
	
	@Override
	public List<ProductVO> searchProductList(String sortBy) {
		List<ProductVO> spvoList = productDAO.searchProductList(sortBy);
		for(int i=0;i<spvoList.size();i++){
			spvoList.get(i).setLowestPrice(productDAO.getLowestPriceByProductId(spvoList.get(i).getProduct_id()));
		}
		System.out.println(spvoList);
		return spvoList;
	}
	
	@Override
	public List<String> getItemList() {
		return productDAO.getItemList();
	}
	
	@Override
	public void addProductWithSellerLinkAndEvaluating(ProductVO pvo, SlvoListVO slvoList, EvoListVO evoList) {
		if(pvo.getThumbnail_link()==null) {
			pvo.setThumbnail_link("img/no_image.png");
		}
		productDAO.addProduct(pvo); //insert는 부모테이블 것을 먼저 해준다
		
		if(slvoList.getSlvoList()!=null) { //공란이 DB에 들어가면 null로 인식되서 not null인 변수에 넣을 수가 없다
			for(int i=0;i<slvoList.getSlvoList().size();i++) {
				slvoList.getSlvoList().get(i).setProduct_id(pvo.getProduct_id());
				productDAO.addSellerLink(slvoList.getSlvoList().get(i));
			}
		}
		
		if(evoList.getEvoList()!=null) {
			for(int i=0;i<evoList.getEvoList().size();i++) {
				evoList.getEvoList().get(i).setProduct_id(pvo.getProduct_id());
				productDAO.addEvaluatingItem(evoList.getEvoList().get(i));
			}
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

	//검색어 순위
	@Override
	public List<ProductVO> selectReport() throws SQLException {
		return productDAO.selectReport();
	}

	@Override
	public void saveReport(String word) throws SQLException {	
		int result=productDAO.updateReport(word);
		if(result==0)
			productDAO.insertReport(word);			
}

	@Override
	public List<ProductVO> getAllProductListByCategory(String sortBy) {
		
		List<String> categoryIdList = productDAO.getCategoryIdByCategory(sortBy);
		
		System.out.println("categoryIdList: "+categoryIdList);
		System.out.println(categoryIdList.get(0));

		List<ProductVO> productListOfListByCategoryId = new ArrayList<ProductVO>();
		List<ProductVO> productListByCategoryId = new ArrayList<ProductVO>();
		for(int i=0;i<categoryIdList.size();i++) {
			try {
				productListOfListByCategoryId = (List<ProductVO>) productDAO.getAllProductListByCategoryId(categoryIdList.get(i)).get(i);
				System.out.println("productListOfListByCategoryId: "+productListOfListByCategoryId);
			} catch(IndexOutOfBoundsException ie) {
				productListByCategoryId = null;
				break;
			}
			
			for(int j=0;j<productListOfListByCategoryId.size();j++){
				productListByCategoryId.add((ProductVO) productListOfListByCategoryId.get(j));
			}
		}
		
		return productListByCategoryId;
	}	
}











