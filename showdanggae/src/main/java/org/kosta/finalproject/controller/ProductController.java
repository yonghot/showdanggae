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
	public ModelAndView getMyProductList(ProductVO pvo) throws Exception {
		return new ModelAndView("product_myProductList", "pvoList", productService.getMyProductList(pvo));
	}
}
 