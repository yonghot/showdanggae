package org.kosta.finalproject.controller;

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

	// 강민석 영역

	// 로그인 상태일때(나의 categoryList + 나의 productList가 표시된다.)
	@RequestMapping("getMainCategoryList.do")
	public ModelAndView getMainCategoryList(HttpServletRequest request) {
		// HttpSession이 존재하면 현재 HttpSession을 반환하고,
		// 존재하지 않으면 새로이 생성하지 않고 그냥 null을 반환한다.
		HttpSession session = request.getSession(false);
		// 장바구니는 세션이 존재해야 하므로 한번더 if문으로 검증한다.
		session.getAttribute("mvo");
		if (session.getAttribute("mvo") != null) {
			List<CategoryVO> maincategorylist = categoryService
					.getMainCategoryList();
			return new ModelAndView("product_myProductList",
					"maincategorylist", maincategorylist);
		} else {
			return new ModelAndView("redirect:login");
		}
	}

	// 로그인 상태일때, 내가 추가해 놓은 카테고리 리스트가 표시된다.
	@RequestMapping("getMyCategoryList.do")
	public ModelAndView getMyCategoryList(String member_id,
			HttpServletRequest request) {
		HttpSession session = request.getSession(false);
		session.getAttribute("mvo");
		if (member_id != null) {
			List<CategoryVO> categorylist = categoryService
					.getMyCategoryList(member_id);
			System.out.println(categorylist);
			return new ModelAndView("product_productList", "categorylist",
					categorylist);
		} else {
			return new ModelAndView("login");
		}
	}

	// 로그인 상태일때, 메인 카테고리로 부터 카테고리를 추가 할 수 있다.
	// 상품정보 추가는 용호.
	// 이 때, 3개의 카테고리 까지만 추가 가능하다.(3개까지 추가가능 옵션은 보류)
	@RequestMapping("addCategory.do")
	public void addCategory(String category, HttpServletRequest request) {
		HttpSession session = request.getSession(false);
		if (session != null) {
			categoryService.addMyCategory(category);
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
	public ModelAndView deleteProduct(int category_id,
			HttpServletRequest request) {
		HttpSession session = request.getSession(false);
		if (session.getAttribute("mvo") != null) {
			productService.deleteProduct(category_id);
		}
		return new ModelAndView("login");
	}

	// 나의 카테고리를 지우기 위해서 하위 상품을 삭제하고 해당 카테고리를 지운다.
	@RequestMapping("deleteProductListAndCategory.do")
	public ModelAndView deleteProductListAndCategory(int category_id,
			HttpServletRequest request) {
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
	
	//로그인 상태일때, 내가 추가해 놓은 상품 리스트가 표시된다.
	@RequestMapping("auth_getMyProductList.do")
	public ModelAndView getMyProductList(String member_id, String currentCategory) throws Exception {
		return new ModelAndView("product_myProductList", "pvoList", productService.getMyProductList(member_id, currentCategory));
	}

	// getAllBoardList
	@RequestMapping("getAllBoardList.do")
	public ModelAndView getAllBoardList(String sortBy) throws Exception {
		return new ModelAndView("product_allProductList", "pvoList",
				productService.getAllBoardList(sortBy));
	}

}
