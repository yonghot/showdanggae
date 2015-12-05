package org.kosta.finalproject.controller;

import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.kosta.finalproject.model.category.CategoryService;
import org.kosta.finalproject.model.category.CategoryVO;
import org.kosta.finalproject.model.product.ProductService;
import org.kosta.finalproject.model.product.ProductVO;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class ProductController {

	@Resource
	private CategoryService categoryService;
	private ProductService productService;
	// 로그인 상태일때, 내가 추가해 놓은 카테고리 리스트가 표시된다.
	@RequestMapping("getMyProductList.do")
<<<<<<< HEAD
	@ResponseBody
	public ModelAndView getMyCategoryList(String member_id, HttpServletRequest request) {
		//HttpSession이 존재하면 현재 HttpSession을 반환하고 존재하지 않으면 새로이 생성하지 않고 그냥 null을 반환한다.
		HttpSession session = request.getSession(false);
		//장바구니는 세션이 존재해야 하므로 한번더 if문으로 검증한다. 
		//로그인 상태일때(나의 categoryList + 나의 productList)
		session.getAttribute("mvo");
		if (member_id != null) {
			// 세션에 멤버정보 추가, request 영역에 category정보와 product정보를 추가
			ModelAndView mv = new ModelAndView();
			// DB에서 받아올 카테고리 리스트를 담을 List객체 categorylist를 준비한다. 편의상 id java님이
			//String id="java";
			//String id=request.getParameter("member_id");
			//System.out.println("아이디 받았다" + id);
			//System.out.println("카테고리 나와라(민석)");
			List<CategoryVO> categorylist = categoryService.getMemberCategoryList(member_id);
			System.out.println(categorylist);
			return new ModelAndView("product_productList", "categorylist", categorylist);
		} else {
			return new ModelAndView("login");
		}
=======
	public ModelAndView getMyProductList(String member_id, String currentCategory) throws Exception {
		return new ModelAndView("product_productList", "pvoList", productService.getMyProductList(member_id, currentCategory));
>>>>>>> branch 'master' of https://github.com/yonghot/showdanggae.git
	}
	
	@RequestMapping("MyProductListDelete.do")
	public ModelAndView MyProductListDelete(int category_id, HttpServletRequest request){
		//HttpSession이 존재하면 현재 HttpSession을 반환하고 존재하지 않으면 새로이 생성하지 않고 그냥 null을 반환한다.
		HttpSession session = request.getSession(false);
		//로그인 되어있는 멤버의 세션을 가져온다.
		session.getAttribute("mvo");
		//카테고리를 삭제 하려면, 카테고리 아래에 저장된 product가 존재해서는 안된다. 
		//만약 존재하면 alert창으로 하위 product를 모두 삭제 하겠습니까? 물어본뒤 
		//예:하위 product삭제 및 카테고리 삭제, 아니오:취소
		
		//product table에 데이터가 존재하는지 알아본다.
		//복수의 product 중에 category_id(멤버가 지우기로 선택한)으로 조회한다.
		
		//test를 위해서 category_id에 1번 카테고리를 넣어본다.
		category_id=1;
		List<ProductVO> productnamelist = productService.getMemberProductListForDeleteCategory(category_id);
		System.out.println(productnamelist+"민석 product");
		return new ModelAndView("product_productList", "productnamelist", productnamelist);	
		}
		
	}

