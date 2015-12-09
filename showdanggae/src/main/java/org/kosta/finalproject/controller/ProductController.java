package org.kosta.finalproject.controller;

import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.kosta.finalproject.model.category.CategoryService;
import org.kosta.finalproject.model.category.CategoryVO;
import org.kosta.finalproject.model.member.MemberVO;
import org.kosta.finalproject.model.product.EvaluatingItemVO;
import org.kosta.finalproject.model.product.ProductService;
import org.kosta.finalproject.model.product.ProductVO;
import org.kosta.finalproject.model.product.SellerLinkVO;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class ProductController {

	@Resource
	private CategoryService categoryService;
	@Resource
	private ProductService productService;

	// 로그인 상태일때, 메인 카테고리로 부터 카테고리를 추가 할 수 있다.
	// 상품정보 추가는 용호.
	// 이 때, 3개의 카테고리 까지만 추가 가능하다.(3개까지 추가가능 옵션은 보류)
	@RequestMapping(value="addCategory.do", method = RequestMethod.POST)
	public void addCategory(String category, HttpServletRequest request) {
		HttpSession session = request.getSession(false);
		//String category = httpServletRequest.getParameter("category");
		if (session != null) {
		}
	}
	// CategoryVO.class 에 private interest 삽입
	@RequestMapping("addInterest.do")
	public void addInterest(String interest, HttpServletRequest request) {
		HttpSession session = request.getSession(false);
		if (session.getAttribute("mvo") != null) {
			session.setAttribute("interest", interest);
			System.out.println("mvo");
			MemberVO vo = (MemberVO) session.getAttribute("mvo");
			categoryService.addInterest(vo);
		}
	}

	// 카테고리를 삭제 하려면, 카테고리 아래에 저장된 product가 존재해서는 안된다.
	// 만약 존재하면 alert창으로 하위 product를 모두 삭제 하겠습니까? 물어본뒤
	// 예:하위 product삭제 및 카테고리 삭제, 아니오:취소
	// 예 선택시 해당 category_id를 가진 product들을 삭제 한 후, 카테고리를 삭제한다.

	// 나의 개별 상품을 지운다.
	@RequestMapping("deleteProduct.do")
	public ModelAndView deleteProduct(int category_id, HttpServletRequest request) {
		HttpSession session = request.getSession(false);
		if (session.getAttribute("mvo") != null) {
			productService.deleteProduct(category_id);
		}
		return new ModelAndView("login");
	}

	// 나의 카테고리를 지우기 위해서 하위 상품을 삭제하고 해당 카테고리를 지운다.
	@RequestMapping("deleteProductListAndCategory.do")
	public ModelAndView deleteProductListAndCategory(int category_id, HttpServletRequest request) {
		HttpSession session = request.getSession(false);
		if (session.getAttribute("mvo") != null) {
			productService.deleteProductList(category_id);
			categoryService.deleteCategory(category_id);
		}
		return new ModelAndView("login");
	}

	// 나의 해당 카테고리를 지운다.
	@RequestMapping("deleteCategory.do")
	public ModelAndView deleteCategory(int category_id,
			HttpServletRequest request) {
		HttpSession session = request.getSession(false);
		if (session.getAttribute("mvo") != null) {
			categoryService.deleteCategory(category_id);
		}
		return new ModelAndView("login");
	}

	
	// 김용호 영역
	
	@RequestMapping(value="auth_ajaxMemberCategoryList.do", method = RequestMethod.POST)
	public ModelAndView AjaxMainCategoryList(String member_id) {
		List<CategoryVO> vo=categoryService.getMemberCategoryList(member_id);
		
		System.out.println("2145435646"+member_id);
		if(vo!=null) {
			return new ModelAndView("ajaxList", "vo", vo);
		}
			return new ModelAndView("ajaxList", "vo", null);
	}
	
	//김용호 영역
	//로그인 상태일때, 내가 추가해 놓은 상품 리스트가 표시된다.
	@RequestMapping("auth_getMyProductList.do")
	public ModelAndView getMyProductList(String member_id, String currentCategory) throws Exception {
		
		ModelAndView mv = new ModelAndView("product_myProductList");
		
		mv.addObject("pvoList", productService.getMyProductList(member_id, currentCategory));
		mv.addObject("mainCategoryList", categoryService.getMainCategoryList());
		mv.addObject("memberCategoryList", categoryService.getMemberCategoryList(member_id));
		mv.addObject("category_id", currentCategory);
		
		return mv;
	}

	//getAllBoardList
	@RequestMapping("getAllBoardList.do")
	public ModelAndView getAllBoardList(String sortBy) throws Exception {
		return new ModelAndView("product_allProductList", "pvoList", productService.getAllBoardList(sortBy));
	}
	
	// beforeGoingRegistProduct
	@RequestMapping("beforeGoingRegistProduct.do")
	public ModelAndView beforeGoingRegistProduct(String category_id) throws Exception {
		
		ModelAndView mv = new ModelAndView("product_registProduct");
		
		mv.addObject("category_id", category_id);
		mv.addObject("itemList", productService.getItemList());
		
		return mv;
	}
	
	// registProduct
	@RequestMapping("registProduct.do")
	public ModelAndView registProduct(ProductVO pvo, SellerLinkVO slvo, EvaluatingItemVO evo) throws Exception {
		//vo에 변수명이 int로 되어있어도 String 데이터가 자동으로 parseInt되면서 들어가는 듯
		productService.addProductWithSellerLinkAndEvaluating(pvo, slvo, evo);
		return new ModelAndView("product/registOk", "currentCategory", pvo.getCategory_id());
	}

}




