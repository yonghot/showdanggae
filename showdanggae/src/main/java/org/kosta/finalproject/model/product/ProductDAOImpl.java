package org.kosta.finalproject.model.product;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class ProductDAOImpl implements ProductDAO {
	@Resource
	private SqlSessionTemplate sqlSessionTemplate;
	
	
	// 강민석 영역
	
	
	@Override
	public void deleteProductList(int category_id) {
		sqlSessionTemplate.delete("product.deleteProductList", category_id);
	}
	
	
	// 김용호 영역
	
	@Override
	public List<ProductVO> getMyProductList(Map<String, String> map) {
		return sqlSessionTemplate.selectList("product.getMyProductList", map);
	}
	@Override
	public List<ProductVO> getAllBoardList(String sortBy) {
		return sqlSessionTemplate.selectList("product.getAllBoardList", sortBy);
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

	@Override
	public int getLowestPriceByProductId(int product_id) {
		return sqlSessionTemplate.selectOne("product.getLowestPriceByProductId", product_id);
	}

}
