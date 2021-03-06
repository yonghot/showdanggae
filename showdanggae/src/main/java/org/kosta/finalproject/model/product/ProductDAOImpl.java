package org.kosta.finalproject.model.product;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class ProductDAOImpl implements ProductDAO {
	@Resource
	private SqlSessionTemplate sqlSessionTemplate;
	
	
	@Override
	public List<ProductVO> getMyProductList(Map<String, String> map) {
		return sqlSessionTemplate.selectList("product.getMyProductList", map);
	}
	@Override
	public List<ProductVO> getAllProductList(String sortBy) {
		return sqlSessionTemplate.selectList("product.getAllProductList", sortBy);
	}
	@Override
	public List<ProductVO> searchProductList(String sortBy) {
		return sqlSessionTemplate.selectList("product.searchProductList", sortBy);
	}
	
	@Override
	public List<String> getItemList() {
		return sqlSessionTemplate.selectList("product.getItemList");
	}
	
	@Override
	public void addProduct(ProductVO pvo) {
		sqlSessionTemplate.insert("product.addProduct", pvo);
	}
	@Override
	public void addSellerLink(SellerLinkVO slvo) {
		sqlSessionTemplate.insert("product.addSellerLink", slvo);
	}
	@Override
	public void addEvaluatingItem(EvaluatingItemVO evo) {
		sqlSessionTemplate.insert("product.addEvaluatingItem", evo);
	}
	@Override
	public void hit(String product_id) {
		sqlSessionTemplate.update("product.hit", product_id);
	}
	
	@Override
	public ProductVO getProductByProduct_id(int product_id) {
		return sqlSessionTemplate.selectOne("product.getProductByProduct_id", product_id);
	}
	@Override
	public List<SellerLinkVO> getSellerLinkByProduct_id(int product_id) {
		return sqlSessionTemplate.selectList("product.getSellerLinkByProduct_id", product_id);
	}
	@Override
	public List<EvaluatingItemVO> getEvaluatingItemByProduct_id(int product_id) {
		return sqlSessionTemplate.selectList("product.getEvaluatingItemByProduct_id", product_id);
	}
	
	@Override
	public void updateProduct(ProductVO pvo) {
		sqlSessionTemplate.update("product.updateProduct", pvo);
	}
	@Override
	public void updateSellerLink(SellerLinkVO slvo) {
		sqlSessionTemplate.update("product.updateSellerLink", slvo);		
	}
	@Override
	public void updateEvaluatingItem(EvaluatingItemVO evo) {
		sqlSessionTemplate.update("product.updateEvaluatingItem", evo);		
	}
	@Override
	public void deleteProduct(int product_id) {
		sqlSessionTemplate.delete("product.deleteProduct", product_id);
	}
	@Override
	public void deleteSellerLink(int product_id) {
		sqlSessionTemplate.delete("product.deleteSellerLink", product_id);		
	}
	@Override
	public void deleteEvaluatingItem(int product_id) {
		sqlSessionTemplate.delete("product.deleteEvaluatingItem", product_id);		
	}

	@Override
	public List<SellerLinkVO> findSellerLinkByProductId(int product_id) {
		return sqlSessionTemplate.selectList("product.findSellerLinkByProductId", product_id);
	}
	@Override
	public List<EvaluatingItemVO> findEvaluatingItemByProductId(int product_id) {
		return sqlSessionTemplate.selectList("product.findEvaluatingItemByProductId", product_id);
	}

	@Override
	public int getCategoryIdByProductId(int product_id) {
		return sqlSessionTemplate.selectOne("product.getCategoryIdByProductId", product_id);
	}

	/**
	 * @Method 이름 : getLowestPriceByProductId
	 * @Method 설명 : 쇼핑 메모 리스트를 불러올 때 썸네일 이미지와 함께 최저가를 카드에 표시해주기 위해 해당 글에 포함된 판매링크별 가격 중 최저가를 추출한다.
	 * @param product_id
	 * @return
	 * @작성일 : 2015. 12. 23.
	 * @작성자 : 용호
	 */
	@Override
	public int getLowestPriceByProductId(int product_id) {
		
		//최저가의 기본값을 0으로 설정하고 판매링크가 있을 경우에만 최저가를 갱신한다.
		int lowestPrice = 0;
		try {
			lowestPrice = sqlSessionTemplate.selectOne("product.getLowestPriceByProductId", product_id);
		} catch(NullPointerException ne) {
		}
		return lowestPrice;
	}
	
	// 검색어 순위
	@Override
	public List<ProductVO> selectReport() throws SQLException {
		return sqlSessionTemplate.selectList("product.selectReport");
	}
	
	public void insertReport(String word) throws SQLException {
		sqlSessionTemplate.insert("product.insertReport", word);
	}

	public int updateReport(String word) throws SQLException {
		return sqlSessionTemplate.update("product.updateReport",word);
	}
	@Override
	public List<ProductVO> getAllProductListByCategoryId(String category_id) {
		return sqlSessionTemplate.selectList("product.getAllProductListByCategoryId",category_id);
	}
	@Override
	public List<String> getCategoryIdByCategory(String sortBy) {
		return sqlSessionTemplate.selectList("product.getCategoryIdByCategory",sortBy);
	}
}
