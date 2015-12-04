package org.kosta.finalproject.controller;

import javax.annotation.Resource;

import org.kosta.finalproject.model.product.ProductService;
import org.kosta.finalproject.model.product.ProductVO;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class ProductController {
	
	@Resource
	private ProductService productService;
	
	@RequestMapping("getMyProductList.do")
	public ModelAndView getMyProductList(String member_id, String currentCategory) throws Exception {
		return new ModelAndView("product_productList", "pvoList", productService.getMyProductList(member_id, currentCategory));
	}
}
 