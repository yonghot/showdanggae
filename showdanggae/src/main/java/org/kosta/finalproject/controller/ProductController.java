package org.kosta.finalproject.controller;

import java.util.HashMap;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.kosta.finalproject.model.category.CategoryService;
import org.kosta.finalproject.model.category.CategoryVO;
import org.kosta.finalproject.model.member.MemberVO;
import org.kosta.finalproject.model.product.ProductService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class ProductController {

	@Resource
	private CategoryService categoryService;
	
	@Resource
	private ProductService productService;
	
//강민석 영역

	
	//로그인 상태일때, 메인 카테고리로 부터 카테고리를 추가 할 수 있다.
	//이 때, 3개의 카테고리 까지만 추가 가능하다.
	@RequestMapping(value="addCategory.do")
	public ModelAndView addCategory(String category, HttpServletRequest request){
		HttpSession session = request.getSession(false);
		MemberVO mvo = (MemberVO) session.getAttribute("mvo");
		
		//카테고리가 3개 초과일 경우 입력불가.(보류)
		List<CategoryVO> categorylist = null;
		if (mvo != null) {
			categoryService.addMyCategory(category);
			categorylist = categoryService.getMemberCategoryList(mvo.getMember_id());
		}
		return new ModelAndView("product_productList", "categorylist", categorylist);
	}
	
	@RequestMapping("MyProductListDelete.do")
	public void MemberProductListAndDeleteCategory(HttpServletRequest request){
		//HttpSession이 존재하면 현재 HttpSession을 반환하고 존재하지 않으면 새로이 생성하지 않고 그냥 null을 반환한다.
		HttpSession session = request.getSession(false);
		//로그인 되어있는 멤버의 세션을 가져온다.
		session.getAttribute("mvo");
		
		//List<ProductVO> pvo=request.getParameter(category_id, productVO);
	}
	
	// 카테고리를 삭제 하려면, 카테고리 아래에 저장된 product가 존재해서는 안된다.
	// 만약 존재하면 alert창으로 하위 product를 모두 삭제 하겠습니까? 물어본뒤
	// 예:하위 product삭제 및 카테고리 삭제, 아니오:취소
	// 예 선택시 해당 category_id를 가진 product들을 삭제 한 후, 카테고리를 삭제한다.

	// 상품지움 클릭~
	@RequestMapping("DeleteProduct.do")
	public ModelAndView DeleteProduct(int category_id,
			HttpServletRequest request) {
		HttpSession session = request.getSession(false);
		if (session.getAttribute("mvo") != null) {
			productService.DeleteProduct(category_id);
		}
		return new ModelAndView("login");
	}

	@RequestMapping("DeleteProductListAndCategory.do")
	public ModelAndView DeleteProductListAndCategory(int category_id,
			HttpServletRequest request) {
		HttpSession session = request.getSession(false);
		if (session.getAttribute("mvo") != null) {
			productService.DeleteProductList(category_id);
			categoryService.DeleteCategory(category_id);
		}
		return new ModelAndView("login");
	}

	@RequestMapping("DeleteCategory.do")
	public ModelAndView DeleteCategory(int category_id, HttpServletRequest request) {
		HttpSession session = request.getSession(false);
		if (session.getAttribute("mvo") != null) {
			categoryService.DeleteCategory(category_id);
		}
		return new ModelAndView("login");
	}
	
	
	// 김용호 영역
	
	//로그인 상태일때, 내가 추가해 놓은 상품 리스트가 표시된다.
	@RequestMapping("getMyProductList.do")
	public ModelAndView getMyProductList(String member_id, String currentCategory) throws Exception {
		HashMap<String, List> productAndCategoryMap = new HashMap<String, List>();
		productAndCategoryMap.put("pvoList", productService.getMyProductList(member_id, currentCategory));
		productAndCategoryMap.put("mainCategoryList", categoryService.getMainCategoryList());
		productAndCategoryMap.put("memberCategoryList", categoryService.getMemberCategoryList(member_id));
		return new ModelAndView("product_myProductList", "productAndCategoryMap", productAndCategoryMap);
	}
	
	
	@RequestMapping("getAllBoardList.do")
	public ModelAndView getAllBoardList(String sortBy) throws Exception {
		return new ModelAndView("product_allProductList", "pvoList", productService.getAllBoardList(sortBy));
	}


	
}
