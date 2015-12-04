package org.kosta.finalproject.model.product;

import java.util.List;

public interface ProductDAO {

	List<ProductVO> getMyProductList(ProductVO pvo);
}
