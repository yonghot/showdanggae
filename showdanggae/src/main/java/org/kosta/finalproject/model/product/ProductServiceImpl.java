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
	
	
	/**
	 * @Method 이름 : getMyProductList
	 * @Method 설명 : 회원 아이디와 카테고리 아이디를 통해 해당 회원의 특정 카테고리에 포함된 쇼핑 메모 리스트를 불러온다.
	 * 				리스트를 불러온 뒤 판매링크 VO에 포함된 각 링크별 판매 가격을 비교하여 최저가를 추출한 뒤 각 pvo에 setting 해준다.
	 * @param member_id
	 * @param currentCategory
	 * @return
	 * @작성일 : 2015. 12. 23.
	 * @작성자 : 용호
	 */
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
	
	/**
	 * @Method 이름 : getAllProductList
	 * @Method 설명 : 카테고리명을 받는 sortBy 인자를 통해 해당 카테고리에 소속된 모든 쇼핑 메모 리스트를 불러온다. sortBy를 지정하지 않으면 모든 회원의 쇼핑 메모 리스트를 불러온다. 그 후 최저가 setting
	 * @param sortBy
	 * @return
	 * @작성일 : 2015. 12. 23.
	 * @작성자 : 용호
	 */
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
	
	/**
	 * @Method 이름 : getItemList
	 * @Method 설명 : 쇼핑 메모 등록, 수정시 평가항목을 선택하기 위해 필요한 평가항목 리스트를 불러온다.
	 * @return
	 * @작성일 : 2015. 12. 23.
	 * @작성자 : 용호
	 */
	@Override
	public List<String> getItemList() {
		return productDAO.getItemList();
	}
	
	/**
	 * @Method 이름 : addProductWithSellerLinkAndEvaluating
	 * @Method 설명 : 쇼핑 메모 등록시 판매링크, 평가항목과 함께 ProductVO를 insert한다. 부모테이블에 해당하는 productVO를 먼저 insert 해준다.
	 * @param pvo
	 * @param slvoList
	 * @param evoList
	 * @작성일 : 2015. 12. 23.
	 * @작성자 : 용호
	 */
	@Override
	public void addProductWithSellerLinkAndEvaluating(ProductVO pvo, SlvoListVO slvoList, EvoListVO evoList) {
		
		//썸네일 이미지가 없을 때 이미지가 없음을 뜻하는 no_image를 대신 삽입해준다.
		if(pvo.getThumbnail_link()==null) {
			pvo.setThumbnail_link("img/no_image.png");
		}
		productDAO.addProduct(pvo);
		
		//pvo insert시 시퀀스로 부여되는 product_id를 selectKey로 추출하여 판매링크와 평가항목 insert시 입력해준다.
		if(slvoList.getSlvoList()!=null) {
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
	
	/**
	 * @Method 이름 : hit
	 * @Method 설명 : 조회수를 올려주는 메소드. 메모 보기 클릭시에만 이 메소드를 거친다
	 * @param product_id
	 * @작성일 : 2015. 12. 23.
	 * @작성자 : 용호
	 */
	@Override
	public void hit(String product_id) {
		productDAO.hit(product_id);
	}
	
	
	/**
	 * @Method 이름 : showProductContent
	 * @Method 설명 : 쇼핑 메모 보기 클릭 시 hit 메소드를 거친 뒤 해당 쇼핑 메모의 정보를 불러온다. 판매링크, 평가항목 리스트와 같이 다수의 정보가 포함되어 있으므로 HashMap을 통해 일괄적으로 controller에 리턴한다.
	 * @param product_id
	 * @return
	 * @작성일 : 2015. 12. 23.
	 * @작성자 : 용호
	 */
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
	
	/**
	 * @Method 이름 : updateProduct
	 * @Method 설명 : 쇼핑 메모 수정 페이지에서 새로 입력받은 정보로 업데이트한다.
	 * 				판매링크와 평가항목의 수정전 후 유무에 따라 조건문을 이용하여 수정이면 update, 추가면 add, 제거이면 delete 메소드를 실행시킨다.
	 * @param product_id
	 * @param pvo
	 * @param slvo
	 * @param evo
	 * @작성일 : 2015. 12. 23.
	 * @작성자 : 용호
	 */
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
	
	
	/**
	 * @Method 이름 : deleteProduct
	 * @Method 설명 : 쇼핑 메모 삭제시 자식 테이블에 해당하는 판매링크와 평가항목을 먼저 삭제한 뒤 부모테이블의 데이터를 삭제한다.
	 * @param product_id
	 * @작성일 : 2015. 12. 23.
	 * @작성자 : 용호
	 */
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

	
	/**
	 * @Method 이름 : getCategoryIdByProductId
	 * @Method 설명 : product
	 * @param product_id
	 * @return
	 * @작성일 : 2015. 12. 23.
	 * @작성자 : 용호
	 */
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

	/**
	 * @Method 이름 : getAllProductListByCategory
	 * @Method 설명 : 
	 * @param sortBy
	 * @return
	 * @작성일 : 2015. 12. 23.
	 * @작성자 : 용호
	 */
	@SuppressWarnings("unchecked")
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











